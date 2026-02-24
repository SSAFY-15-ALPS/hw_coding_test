import java.util.*;

public class Main {
	static int n;
	static int[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		for (int i = 1; i < n; i++) {
			int target = arr[i];
			for (int j = 0; j < i; j++) {
				if (target > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		int answer = 0;
		for(int i : dp) {
			answer = Math.max(answer, i);
		}
		System.out.println(answer);
		sc.close();
	}

}
