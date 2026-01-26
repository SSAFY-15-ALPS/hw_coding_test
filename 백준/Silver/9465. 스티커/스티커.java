import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < t; test_case++) {
			int n = Integer.parseInt(br.readLine());

			int[][] stickers = new int[2][n];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					stickers[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// dp[i][j] = i번째 열에서 j행 스티커를 선택했을 때 최댓값
			// j=0: 위쪽, j=1: 아래쪽, j=2: 선택 안 함
			int[][] dp = new int[n][3];

			dp[0][0] = stickers[0][0];
			dp[0][1] = stickers[1][0];
			dp[0][2] = 0;

			for (int i = 1; i < n; i++) {
				dp[i][0] = Math.max(dp[i-1][1], dp[i-1][2]) + stickers[0][i];
				dp[i][1] = Math.max(dp[i-1][0], dp[i-1][2]) + stickers[1][i];
				dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
			}

			System.out.println(Math.max(dp[n-1][0], Math.max(dp[n-1][1], dp[n-1][2])));
		}
	}
}