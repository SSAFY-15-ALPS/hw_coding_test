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

async function generateContent(code, problemInfo, readmeContent) {
  const language = problemInfo.language || 'java';

  const message = await anthropic.messages.create({
    model: 'claude-sonnet-4-20250514',
    max_tokens: 8000,
    system: `너는 알고리즘 문제를 직접 푼 대학생 개발자야. 문제를 풀고 나서 자기 블로그에 풀이를 정리하는 중이야.
절대 AI가 쓴 것처럼 쓰지 마. 아래 규칙을 반드시 지켜:
- 1인칭 시점으로 작성 ("~했다", "~인 것 같다", "~해서 좀 헤맸다")
- 교과서적인 설명 금지. 본인이 실제로 풀면서 겪은 것처럼 자연스럽게
- 뻔한 말 반복 금지 ("이 문제는 ~를 활용하는 문제이다" 같은 식 X)
- 구체적으로 코드의 어떤 부분이 왜 그렇게 짜여졌는지 설명
- 틀릴 뻔했거나 실수하기 쉬운 부분을 구체적으로 짚어줘
- 시간복잡도/공간복잡도 분석을 자연스럽게 녹여서 설명
- 다른 접근법이 있다면 왜 이 방법을 선택했는지도 언급`,
    messages: [
      {
        role: 'user',
        content: `아래 문제 풀이에 대한 상세한 회고를 작성해줘.

## 문제 정보
- 문제: ${problemInfo.title}
- 플랫폼: ${problemInfo.platform}
- 문제번호: ${problemInfo.problemNumber}
- 난이도: ${problemInfo.difficulty || '알 수 없음'}
${problemInfo.url ? `- 링크: ${problemInfo.url}` : ''}

${readmeContent ? `## 문제 설명 (BaekjoonHub README)\n${readmeContent}\n` : ''}

## 내 코드
\`\`\`${language}
${code}
\`\`\`

---

아래 형식대로 마크다운을 작성해줘. 각 섹션을 충실하게, 구체적으로 써줘.

# 문제 분석
(문제가 결국 뭘 요구하는 건지 내 말로 정리. 입출력 조건에서 주의할 점, 제약 조건이 풀이 방향에 어떤 영향을 주는지)

# 접근 방식
(처음에 어떻게 접근했는지. 왜 이 알고리즘/자료구조를 선택했는지. 다른 방법도 떠올렸다면 왜 이걸로 갔는지. 시간복잡도 분석도 자연스럽게 포함)

# 풀이 과정
(코드를 단계별로 설명. 그냥 "입력받고 처리한다"가 아니라, 핵심 로직이 왜 그렇게 동작하는지 구체적으로. 변수나 배열의 역할, 조건문의 의미 등)

# 주의할 점 & 실수하기 쉬운 부분
(엣지 케이스, 인덱스 실수, 범위 처리, 초기값 설정 등 구체적으로. "이거 빼먹으면 틀린다" 수준으로)

# 최종 코드
\`\`\`${language}
${code}
\`\`\`

# TIL (Today I Learned)
(이 문제를 통해 배운 것, 다음에 비슷한 문제를 만나면 어떻게 접근할지, 더 나은 풀이가 있다면 어떤 건지)`,
      },
    ],
  });

  return message.content[0].text;
}

function detectLanguage(filePath) {
  if (filePath.endsWith('.java')) return 'java';
  if (filePath.endsWith('.py')) return 'python';
  if (filePath.endsWith('.cpp') || filePath.endsWith('.cc')) return 'cpp';
  return 'plain text';
}

function parseFilePath(filePath) {
  const parts = filePath.split('/');
  const language = detectLanguage(filePath);

  // 백준 (난이도별 폴더 구조)
  if (parts[0] === '백준') {
    const difficulty = parts[1]; // Bronze, Silver, Gold, Platinum, Diamond
    const folderName = parts[2]; // "10998.A×B"
    const match = folderName.match(/^(\d+)\.(.*)/);

    return {
      platform: '백준',
      problemNumber: match ? match[1] : '',
      title: match ? match[2].replace(/_/g, ' ').replace(/×/g, 'x') : folderName,
      difficulty: difficulty,
      url: match ? `https://www.acmicpc.net/problem/${match[1]}` : '',
      language,
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
      language,
    };
  }

  // 프로그래머스 등 다른 플랫폼
  return {
    platform: parts[0],
    title: parts[1]?.replace(/_/g, ' ') || 'Unknown',
    problemNumber: '',
    difficulty: null,
    url: '',
    language,
  };
}

function extractTags(content, readmeContent) {
  const tags = ['Algorithm', 'PS'];

  const combined = (content + ' ' + readmeContent).toLowerCase();

  const tagRules = [
    { tag: 'DP', keywords: ['dp', '동적 계획법', 'dynamic programming', '메모이제이션', 'memoization'] },
    { tag: 'DFS', keywords: ['dfs', '깊이 우선', 'depth first', '백트래킹', 'backtracking'] },
    { tag: 'BFS', keywords: ['bfs', '너비 우선', 'breadth first'] },
    { tag: 'Greedy', keywords: ['그리디', 'greedy', '탐욕'] },
    { tag: 'Binary Search', keywords: ['이분 탐색', 'binary search', '이진 탐색'] },
    { tag: 'Graph', keywords: ['그래프', 'graph', '다익스트라', 'dijkstra', '플로이드', 'floyd'] },
    { tag: 'Tree', keywords: ['트리', 'tree'] },
    { tag: 'Implementation', keywords: ['구현', 'implementation', '시뮬레이션', 'simulation'] },
    { tag: 'String', keywords: ['문자열', 'string', '파싱', 'parsing'] },
    { tag: 'Math', keywords: ['수학', 'math', '정수론', '소수', 'prime'] },
    { tag: 'Stack/Queue', keywords: ['스택', 'stack', '큐', 'queue', 'deque'] },
    { tag: 'Sorting', keywords: ['정렬', 'sorting', 'sort'] },
    { tag: 'Divide and Conquer', keywords: ['분할 정복', 'divide and conquer'] },
    { tag: 'Heap', keywords: ['힙', 'heap', '우선순위 큐', 'priority queue'] },
    { tag: 'Brute Force', keywords: ['브루트포스', 'brute force', '완전 탐색', '완전탐색'] },
  ];

  for (const { tag, keywords } of tagRules) {
    if (keywords.some(kw => combined.includes(kw))) {
      tags.push(tag);
    }
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
  const changedFiles = process.env.CHANGED_FILES?.trim().split('\n').filter(Boolean) || [];

  if (changedFiles.length === 0) {
    console.log('No code files changed.');
    return;
  }

  for (let file of changedFiles) {
    try {
      console.log(`\n📝 Processing: ${file}`);

      // BaekjoonHub는 폴더명에 U+2005(FOUR-PER-EM SPACE)를 사용함
      // workflow_dispatch 등에서 일반 공백으로 입력된 경우 변환
      if (!fs.existsSync(file)) {
        const fixedFile = file.replace(/ /g, '\u2005');
        if (fs.existsSync(fixedFile)) {
          file = fixedFile;
        } else {
          console.log(`⚠️  File not found: ${file}`);
          continue;
        }
      }
      
      const code = fs.readFileSync(file, 'utf-8');
      const problemInfo = parseFilePath(file);

      // 난이도 필터: 백준 Gold 이상, SWEA D4 이상만 AI 분석
      const allowedBaekjoon = ['Gold', 'Platinum', 'Diamond', 'Ruby'];
      const allowedSwea = ['D4', 'D5'];
      if (problemInfo.platform === '백준' && !allowedBaekjoon.includes(problemInfo.difficulty)) {
        console.log(`⏭️  Skipped (${problemInfo.difficulty}): 백준 Gold 이상만 분석`);
        continue;
      }
      if (problemInfo.platform === 'SWEA' && !allowedSwea.includes(problemInfo.difficulty)) {
        console.log(`⏭️  Skipped (${problemInfo.difficulty}): SWEA D4 이상만 분석`);
        continue;
      }

      // BaekjoonHub가 생성한 README에서 문제 설명, 분류, 성능 정보 읽기
      const readmePath = file.replace(/[^/]+$/, 'README.md');
      let readmeContent = '';
      if (fs.existsSync(readmePath)) {
        readmeContent = fs.readFileSync(readmePath, 'utf-8');
      }

      console.log('🤖 Generating content with Claude...');
      const content = await generateContent(code, problemInfo, readmeContent);
      
      console.log('🏷️  Extracting tags...');
      const tags = extractTags(content, readmeContent);
      
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