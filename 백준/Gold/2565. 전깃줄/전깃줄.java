import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[][] wires = new int[n][2];
		for (int i = 0; i < n; i++) {
			wires[i][0] = sc.nextInt();
			wires[i][1] = sc.nextInt();
		}

		Arrays.sort(wires, Comparator.comparingInt(a -> a[0]));

		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			b[i] = wires[i][1];
		}

		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (b[j] < b[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		int lis = Arrays.stream(dp).max().getAsInt();
		System.out.println(n - lis);

		sc.close();
	}
}