

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		int[] w = new int[n + 1];
		int[] v = new int[n + 1];
		int[][] dp = new int[n + 1][k + 1];

		for (int i = 1; i < n + 1; i++) {
			w[i] = sc.nextInt();
			v[i] = sc.nextInt();
		}

		for (int i = 1; i < k + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (w[j] > i) {
					dp[j][i] = dp[j - 1][i];
				} else {
					dp[j][i] = Math.max(dp[j - 1][i], v[j] + dp[j - 1][i - w[j]]);
				}
			}
		}

		System.out.println(dp[n][k]);

		sc.close();
	}

}
