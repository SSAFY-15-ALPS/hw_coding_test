import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int d = sc.nextInt();
		int k = sc.nextInt();

		int[][] dp = new int[d + 1][2];
		dp[1][0] = 1;
		dp[1][1] = 0;
		dp[2][0] = 0;
		dp[2][1] = 1;
		for (int i = 3; i <= d; i++) {
			dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
			dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
		}

		int a = 1;
		int b = -1;
		while (dp[d][0] * a < k) {
			if ((k - dp[d][0] * a) % dp[d][1] == 0) {
				b = (k - dp[d][0] * a) / dp[d][1];
				break;
			}
			a++;
		}
		System.out.println(a);
		System.out.println(b);

		sc.close();
	}
}