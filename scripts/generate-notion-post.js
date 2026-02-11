import Anthropic from '@anthropic-ai/sdk';
import { Client } from '@notionhq/client';
import fs from 'fs';
import { markdownToBlocks } from './markdown-to-blocks.js';

const anthropic = new Anthropic({
  apiKey: process.env.ANTHROPIC_API_KEY,
});

const notion = new Client({
  auth: process.env.NOTION_TOKEN,
});

async function generateContent(code, problemInfo) {
  const message = await anthropic.messages.create({
    model: 'claude-sonnet-4-20250514',
    max_tokens: 4000,
    messages: [
      {
        role: 'user',
        content: `다음 백준 문제 풀이 코드를 분석해서 상세한 회고를 작성해줘.

문제: ${problemInfo.title}
플랫폼: ${problemInfo.platform}
문제번호: ${problemInfo.problemNumber}

코드:
\`\`\`java
${code}
\`\`\`

아래 형식으로 마크다운을 작성해줘:

# 접근 방식
(코드를 분석해서 어떤 알고리즘/자료구조를 사용했는지, 왜 이 방법을 선택했을지 설명)

# 신경써야 할 부분
* (핵심 포인트 1)
* (핵심 포인트 2)
* (핵심 포인트 3)

# 풀이 과정
(코드의 주요 로직을 단계별로 설명. 초기화 → 메인 로직 → 결과 출력 순서로)

# 최종 코드
\`\`\`java
${code}
\`\`\`

# TIL

## 배웠던 점
* (이 문제에서 배운 알고리즘 개념)
* (새로 알게 된 테크닉)
* (성능 최적화 방법)

## 어려웠던 점
* (코드를 보고 추론한 어려웠을 부분 1)
* (코드를 보고 추론한 어려웠을 부분 2)

중요: 실제로 코드를 작성한 사람의 입장에서, 1인칭 시점으로 작성해줘. "~했다", "~를 깨달았다" 같은 톤으로.`,
      },
    ],
  });

  return message.content[0].text;
}

function parseFilePath(filePath) {
  const parts = filePath.split('/');
  
  // 백준 (난이도별 폴더 구조)
  if (parts[0] === '백준') {
    const difficulty = parts[1]; // Bronze, Silver, Gold, Platinum, Diamond
    const folderName = parts[2]; // "10998.A×B"
    const match = folderName.match(/^(\d+)\.(.*)/);
    
    return {
      platform: '백준',
      problemNumber: match ? match[1] : '',
      title: match ? match[2].replace(/_/g, ' ').replace(/×/g, 'x') : folderName,
      difficulty: difficulty, // Bronze, Silver 등
      url: match ? `https://www.acmicpc.net/problem/${match[1]}` : '',
    };
  }
  
  // SWEA
  if (parts[0] === 'SWEA') {
    const level = parts[1]; // D1, D2, D3, D4, D5, Algorithm_track_level_*
    const folderName = parts[2];
    
    // SWEA 파일명 파싱 (보통 "1234.문제이름" 형태)
    const match = folderName.match(/^(\d+)\.(.*)/);
    
    return {
      platform: 'SWEA',
      problemNumber: match ? match[1] : '',
      title: match ? match[2].replace(/_/g, ' ') : folderName,
      difficulty: level,
      url: match ? `https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=${match[1]}` : '',
    };
  }
  
  // 프로그래머스 등 다른 플랫폼
  return {
    platform: parts[0],
    title: parts[1]?.replace(/_/g, ' ') || 'Unknown',
    problemNumber: '',
    difficulty: null,
    url: '',
  };
}

function extractTags(content) {
  const tags = ['Algorithm', 'PS'];
  
  const lowerContent = content.toLowerCase();
  
  if (lowerContent.includes('dp') || lowerContent.includes('동적 계획법') || lowerContent.includes('dynamic programming')) {
    tags.push('DP');
  }
  if (lowerContent.includes('dfs') || lowerContent.includes('깊이 우선 탐색')) {
    tags.push('DFS');
  }
  if (lowerContent.includes('bfs') || lowerContent.includes('너비 우선 탐색')) {
    tags.push('BFS');
  }
  if (lowerContent.includes('그리디') || lowerContent.includes('greedy')) {
    tags.push('Greedy');
  }
  if (lowerContent.includes('이분 탐색') || lowerContent.includes('binary search')) {
    tags.push('Binary Search');
  }
  if (lowerContent.includes('그래프')) {
    tags.push('Graph');
  }
  if (lowerContent.includes('트리')) {
    tags.push('Tree');
  }
  
  return tags;
}

async function postToNotion(title, content, problemInfo, tags) {
  try {
    const blocks = markdownToBlocks(content);
    
    const MAX_BLOCKS = 100;
    const initialBlocks = blocks.slice(0, MAX_BLOCKS);
    
    const properties = {
      '이름': {
        title: [{ text: { content: title } }],
      },
      'Platform': {
        select: { name: problemInfo.platform },
      },
      '태그': {
        multi_select: tags.map(tag => ({ name: tag })),
      },
    };
    
    // 난이도가 있으면 추가
    if (problemInfo.difficulty) {
      properties['난이도'] = {
        select: { name: problemInfo.difficulty },
      };
    }
    
    // 문제번호가 있으면 추가
    if (problemInfo.problemNumber) {
      properties['문제번호'] = {
        number: parseInt(problemInfo.problemNumber),
      };
    }
    
    // URL이 있으면 추가
    if (problemInfo.url) {
      properties['URL'] = {
        url: problemInfo.url,
      };
    }
    
    const response = await notion.pages.create({
      parent: {
        database_id: process.env.NOTION_DATABASE_ID,
      },
      properties: properties,
      children: initialBlocks,
    });
    
    // 100개 넘는 블록은 추가로 append
    if (blocks.length > MAX_BLOCKS) {
      const remainingBlocks = blocks.slice(MAX_BLOCKS);
      
      for (let i = 0; i < remainingBlocks.length; i += MAX_BLOCKS) {
        const chunk = remainingBlocks.slice(i, i + MAX_BLOCKS);
        await notion.blocks.children.append({
          block_id: response.id,
          children: chunk,
        });
      }
    }
    
    console.log(`✅ Notion page created: ${response.url}`);
    return response;
  } catch (error) {
    console.error('❌ Error creating Notion page:');
    console.error('Message:', error.message);
    if (error.body) {
      console.error('Details:', JSON.stringify(error.body, null, 2));
    }
    throw error;
  }
}

async function main() {
  const changedFiles = process.env.CHANGED_FILES?.trim().split(' ').filter(Boolean) || [];
  
  if (changedFiles.length === 0) {
    console.log('No code files changed.');
    return;
  }

  for (const file of changedFiles) {
    try {
      console.log(`\n📝 Processing: ${file}`);
      
      if (!fs.existsSync(file)) {
        console.log(`⚠️  File not found: ${file}`);
        continue;
      }
      
      const code = fs.readFileSync(file, 'utf-8');
      const problemInfo = parseFilePath(file);
      
      console.log('🤖 Generating content with Claude...');
      const content = await generateContent(code, problemInfo);
      
      console.log('🏷️  Extracting tags...');
      const tags = extractTags(content);
      
      const title = `[${problemInfo.platform}] ${problemInfo.problemNumber ? `${problemInfo.problemNumber}. ` : ''}${problemInfo.title}`;
      
      console.log('📤 Posting to Notion...');
      await postToNotion(title, content, problemInfo, tags);
      
      console.log('✨ Done!\n');
    } catch (error) {
      console.error(`❌ Error processing ${file}:`, error.message);
    }
  }
}

main().catch(console.error);