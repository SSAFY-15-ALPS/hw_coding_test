import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][3];
		int[][] dp = new int[n][3];
		int[][] dp_min = new int[n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		dp[0] = arr[0];
		dp_min[0] = arr[0];

		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.max(dp[i - 1][0] + arr[i][0], dp[i - 1][1] + arr[i][0]);
			dp[i][1] = Math.max(Math.max(dp[i - 1][0] + arr[i][1], dp[i - 1][1] + arr[i][1]), dp[i - 1][2] + arr[i][1]);
			dp[i][2] = Math.max(dp[i - 1][1] + arr[i][2], dp[i - 1][2] + arr[i][2]);
			dp_min[i][0] = Math.min(dp_min[i - 1][0] + arr[i][0], dp_min[i - 1][1] + arr[i][0]);
			dp_min[i][1] = Math.min(Math.min(dp_min[i - 1][0] + arr[i][1], dp_min[i - 1][1] + arr[i][1]),
					dp_min[i - 1][2] + arr[i][1]);
			dp_min[i][2] = Math.min(dp_min[i - 1][1] + arr[i][2], dp_min[i - 1][2] + arr[i][2]);
		}

		System.out.println(Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]) + " "
				+ Math.min(Math.min(dp_min[n - 1][0], dp_min[n - 1][1]), dp_min[n - 1][2]));

	}
}
