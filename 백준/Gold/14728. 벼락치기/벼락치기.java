import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int t = sc.nextInt();
		int[] k = new int[n];
		int[] s = new int[n];
		int[][] dp = new int[n + 1][t + 1];
		for (int i = 0; i < n; i++) {
			k[i] = sc.nextInt();
			s[i] = sc.nextInt();
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= t; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= k[i-1]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - k[i-1]] + s[i-1]);
				}
			}
		}

		System.out.println(dp[n][t]);
		sc.close();
	}
}