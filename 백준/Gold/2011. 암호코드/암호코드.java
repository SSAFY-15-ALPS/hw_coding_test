import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		String s = st.nextToken();
		String[] str = s.split("");

		int[] dp = new int[str.length + 1];
		if (str[0].equals("0")) {
			System.out.println(0);
			return;
		}
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= str.length; i++) {
			int two = Integer.parseInt(str[i - 2]) * 10 + Integer.parseInt(str[i - 1]);
			int one = Integer.parseInt(str[i - 1]);

			if (one >= 1) {                    // 단독 해석 가능
				dp[i] += dp[i - 1];
			}
			if (two >= 10 && two <= 26) {      // 짝 해석 가능
				dp[i] += dp[i - 2];
			}
			dp[i] %= 1000000;                  // 오버플로 방지용으로 매번 모듈러
		}
		System.out.println(dp[str.length]);
	}
	// 2511 -> 2 -> 2/5,25 -> 2/5/1, 25/1 -> 2/5/1/1, 2/5/11, 25/1/1, 25/11 -> 2/5/1/1/4, 2/5
}