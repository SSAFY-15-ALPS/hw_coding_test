# 알고리즘 문제 풀이 저장소

SSAFY 알고리즘 스터디를 위한 코딩테스트 문제 풀이 저장소입니다.

## 프로젝트 정보

- **언어**: Java 8
- **빌드 도구**: Gradle
- **플랫폼**: 백준(Baekjoon), SWEA(SW Expert Academy)

## 프로젝트 구조

```
hw_coding_test/
├── 백준/
│   ├── Silver/
│   │   ├── 1260. DFS와 BFS/
│   │   ├── 2606. 바이러스/
│   │   ├── 2667. 단지번호붙이기/
│   │   └── 9465. 스티커/
│   └── Gold/
│       └── 1074. Z/
│
├── SWEA/
│   ├── D3/
│   │   └── 4047. 영준이의 카드 카운팅/
│   ├── Algorithm_track_level_하/
│   │   ├── D1_1대1_가위바위보.java
│   │   ├── D1_자릿수_더하기.java
│   │   └── D1_중간값_찾기.java
│   └── Algorithm_track_level_중/
│       └── D2_파리퇴치3.java
│
├── build.gradle
├── settings.gradle
└── gradlew
```

## 문제 목록

### 백준 (Baekjoon Online Judge)

| 번호 | 문제명 | 난이도 | 알고리즘 | 실행 시간 | 메모리 |
|:---:|--------|:------:|----------|:---------:|:------:|
| 1074 | [Z](백준/Gold/1074.%20Z/) | Gold V | 분할 정복, 재귀 | 172ms | 17,704KB |
| 1260 | [DFS와 BFS](백준/Silver/1260.%20DFS와%20BFS/) | Silver II | 그래프 탐색, DFS, BFS | 280ms | 20,928KB |
| 2606 | [바이러스](백준/Silver/2606.%20바이러스/) | Silver III | 그래프 탐색, DFS | 104ms | 14,316KB |
| 2667 | [단지번호붙이기](백준/Silver/2667.%20단지번호붙이기/) | Silver I | 그래프 탐색, DFS/BFS | 120ms | 14,716KB |
| 9465 | [스티커](백준/Silver/9465.%20스티커/) | Silver I | 동적 계획법 | 616ms | 129,836KB |

### SWEA (SW Expert Academy)

| 문제명 | 난이도 | 알고리즘 |
|--------|:------:|----------|
| 영준이의 카드 카운팅 | D3 | 문자열, HashMap |
| 파리퇴치3 | D2 | 2D 배열 탐색 |
| 1대1 가위바위보 | D1 | 조건문 |
| 자릿수 더하기 | D1 | 문자열, 수학 |
| 중간값 찾기 | D1 | 정렬 |

## 알고리즘 분류

| 알고리즘 | 문제 수 | 관련 문제 |
|----------|:-------:|-----------|
| DFS/BFS | 3 | 1260, 2606, 2667 |
| 동적 계획법(DP) | 1 | 9465 |
| 분할 정복 | 1 | 1074 |
| 문자열/카운팅 | 2 | 영준이의 카드, 자릿수 더하기 |
| 2D 배열 탐색 | 1 | 파리퇴치3 |

## 사용 방법

### IntelliJ IDEA에서 실행

1. `File` → `Open` → 프로젝트 폴더 선택
2. Gradle 프로젝트로 자동 인식
3. Java 파일 열고 `Ctrl+Shift+F10` 또는 우클릭 → `Run`

### 터미널에서 실행

```bash
# 빌드
./gradlew build

# 특정 Java 파일 컴파일 및 실행 (예시)
javac -encoding UTF-8 "백준/Silver/1260. DFS와 BFS/DFS와 BFS.java"
java -cp "백준/Silver/1260. DFS와 BFS" Main
```

## 참고 링크

- [백준 온라인 저지](https://www.acmicpc.net/)
- [SW Expert Academy](https://swexpertacademy.com/)
- [BaekjoonHub](https://github.com/BaekjoonHub/BaekjoonHub) - 백준 문제 자동 커밋 도구
