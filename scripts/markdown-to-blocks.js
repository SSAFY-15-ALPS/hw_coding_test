export function markdownToBlocks(markdown) {
  const blocks = [];
  const lines = markdown.split('\n');
  
  let i = 0;
  while (i < lines.length) {
    const line = lines[i];
    
    // 빈 줄 스킵
    if (!line.trim()) {
      i++;
      continue;
    }
    
    // 헤더
    if (line.startsWith('# ')) {
      blocks.push({
        object: 'block',
        type: 'heading_1',
        heading_1: {
          rich_text: [{ type: 'text', text: { content: line.slice(2) } }],
        },
      });
    } else if (line.startsWith('## ')) {
      blocks.push({
        object: 'block',
        type: 'heading_2',
        heading_2: {
          rich_text: [{ type: 'text', text: { content: line.slice(3) } }],
        },
      });
    } else if (line.startsWith('### ')) {
      blocks.push({
        object: 'block',
        type: 'heading_3',
        heading_3: {
          rich_text: [{ type: 'text', text: { content: line.slice(4) } }],
        },
      });
    }
    // 코드 블록
    else if (line.startsWith('```')) {
      const language = line.slice(3).trim() || 'plain text';
      const codeLines = [];
      i++;
      
      while (i < lines.length && !lines[i].startsWith('```')) {
        codeLines.push(lines[i]);
        i++;
      }
      
      const codeContent = codeLines.join('\n');
      // Notion 코드 블록은 2000자 제한
      if (codeContent.length > 1900) {
        // 긴 코드는 여러 블록으로 분할
        const chunks = codeContent.match(/.{1,1900}/g) || [];
        chunks.forEach((chunk, idx) => {
          blocks.push({
            object: 'block',
            type: 'code',
            code: {
              rich_text: [{ type: 'text', text: { content: chunk } }],
              language: language === 'java' ? 'java' : language === 'python' ? 'python' : 'plain text',
            },
          });
        });
      } else {
        blocks.push({
          object: 'block',
          type: 'code',
          code: {
            rich_text: [{ type: 'text', text: { content: codeContent } }],
            language: language === 'java' ? 'java' : language === 'python' ? 'python' : 'plain text',
          },
        });
      }
    }
    // 불릿 리스트
    else if (line.startsWith('* ') || line.startsWith('- ')) {
      blocks.push({
        object: 'block',
        type: 'bulleted_list_item',
        bulleted_list_item: {
          rich_text: [{ type: 'text', text: { content: line.slice(2) } }],
        },
      });
    }
    // 일반 텍스트
    else {
      blocks.push({
        object: 'block',
        type: 'paragraph',
        paragraph: {
          rich_text: parseRichText(line),
        },
      });
    }
    
    i++;
  }
  
  return blocks;
}

function parseRichText(text) {
  const richText = [];
  
  // **볼드** 파싱
  const parts = text.split(/(\*\*.*?\*\*)/g);
  
  parts.forEach(part => {
    if (part.startsWith('**') && part.endsWith('**')) {
      richText.push({
        type: 'text',
        text: { content: part.slice(2, -2) },
        annotations: { bold: true },
      });
    } else if (part) {
      richText.push({
        type: 'text',
        text: { content: part },
      });
    }
  });
  
  return richText.length > 0 ? richText : [{ type: 'text', text: { content: text } }];
}