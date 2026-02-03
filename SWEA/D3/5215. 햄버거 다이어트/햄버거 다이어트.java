import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test = 1; test <= T; test++) {
			int n = sc.nextInt();  // 재료 개수
			int l = sc.nextInt();  // 칼로리 제한
			int[] w = new int[n + 1];  // 칼로리
			int[] v = new int[n + 1];  // 맛 점수
			int[][] dp = new int[n + 1][l + 1];

			for (int i = 1; i <= n; i++) {
				v[i] = sc.nextInt();  // 맛 점수
				w[i] = sc.nextInt();  // 칼로리
			}
			
			// i: 물건(재료) 번호, j: 무게(칼로리)
			for(int i = 1; i <= n; i++) {           // 물건을 하나씩 고려
				for(int j = 1; j <= l; j++) {       // 각 무게에 대해
					if(w[i] > j) {
						// i번 재료를 못 넣는 경우
						dp[i][j] = dp[i-1][j];
					} else {
						// i번 재료를 넣을지 말지 선택
						dp[i][j] = Math.max(
							dp[i-1][j],                    // 안 넣기
							v[i] + dp[i-1][j - w[i]]       // 넣기
						);
					}
				}
			}
			
			System.out.println("#" + test + " " + dp[n][l]);
		}
		sc.close();
	}
}