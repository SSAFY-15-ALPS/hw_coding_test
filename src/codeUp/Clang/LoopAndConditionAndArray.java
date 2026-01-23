package codeUp.Clang;

public class LoopAndConditionAndArray {


	public static void main(String[] args) {
		/* 2차원 배열 탐색 연습하기 */

		int[][] scoreList = {
			{10, 85, 60, 70, 95},
			{100, 80, 70, 90, 100},
			{60, 70, 75, 60, 80},
			{10, 30, 20, 40, 40},
			{95, 100, 95, 100, 95}
		};

		// Q. 전체 점수, 점수의 개수, 평균을 저장하기 위한 데이터 타입을 작성하고,
		//    scoreList에 저장된 점수를 모두 더한 결과를 totalScore에
		//    각 점수의 개수를 scoreCount에
		//    그 평균을 avgScore에 저장하여 출력해 봅시다.
		int totalScore = 0;
		int scoreCount = 0;
		int avgScore = 0;

		for(int rowIdx = 0; rowIdx < scoreList.length; rowIdx++) {
			for(int colIdx = 0; colIdx < scoreList[rowIdx].length; colIdx++) {
				totalScore += scoreList[rowIdx][colIdx];
				scoreCount++;
			}
		}

		avgScore = totalScore / scoreCount;

		System.out.println("scoreList의 평균: " + avgScore);


		/* 2차원 배열 조건 탐색 연습하기 */

		char[][] alphaList = {
			{'A', 'E', 'I', 'O', 'U'},
			{'B', 'A', 'N', 'A', 'N', 'A'},
			{'C', 'A', 'R'},
			{'C', 'A', 'P'}
		};

		// Q. alphaList에 포함된 문자를 살펴,
		//    A라면 100점을
		//    B라면 90점을
		//    C라면 80점을 더하는 코드를 작성해 봅시다.

		totalScore = 0;

		for(int rowIdx = 0; rowIdx < alphaList.length; rowIdx++) {
			for(int colIdx = 0; colIdx < alphaList[rowIdx].length; colIdx++) {
				if(alphaList[rowIdx][colIdx]== 'A') {
					totalScore += 100;
				}
				else if(alphaList[rowIdx][colIdx] == 'B') {
					totalScore += 90;
				}
				else if(alphaList[rowIdx][colIdx] == 'C') {
					totalScore += 80;
				}
			}
		}

		System.out.println("ABC 합산 점수: " + totalScore);
	}
}
