
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // 변수명 수정
		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();

		int ans = 0;
		while (n != 0) {
			int half = (int) Math.pow(2, n - 1); // 사분면 크기

			if (r >= half) {
				if (c >= half) {
					ans += half * half * 3; // 4사분면
					r -= half;
					c -= half;
				} else {
					ans += half * half * 2; // 3사분면
					r -= half;
				}
			} else {
				if (c >= half) {
					ans += half * half; // 2사분면
					c -= half;
				}
				// 1사분면은 아무것도 안 더함
			}
			n--;
		}

		System.out.println(ans);

		sc.close();
	}
}
