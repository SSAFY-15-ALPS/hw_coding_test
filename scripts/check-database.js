import { Client } from '@notionhq/client';

const notion = new Client({
  auth: process.env.NOTION_TOKEN,
});

async function checkDatabase() {
  try {
    const database = await notion.databases.retrieve({
      database_id: process.env.NOTION_DATABASE_ID,
    });
    
    console.log('✅ Database found!');
    console.log('📌 Database Title:', database.title[0]?.plain_text || 'Untitled');
    console.log('\n📋 Properties:');
    
    for (const [key, value] of Object.entries(database.properties)) {
      console.log(`  - "${key}" (type: ${value.type})`);
    }
    
    console.log('\n💡 스크립트에서 사용할 속성명을 위 결과에 맞춰 수정하세요!');
  } catch (error) {
    console.error('❌ Error:', error.message);
    console.error('Status:', error.status);
    
    if (error.code === 'object_not_found') {
      console.log('\n💡 해결 방법:');
      console.log('1. Database ID가 정확한지 확인');
      console.log('2. Notion Database 페이지에서 ··· → 연결 → Integration 추가');
    }
  }
}

checkDatabase();