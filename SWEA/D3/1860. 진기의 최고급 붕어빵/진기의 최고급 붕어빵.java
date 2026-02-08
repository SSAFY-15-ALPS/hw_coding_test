

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test = 1; test <= T; test++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int k = sc.nextInt();
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}

			Arrays.sort(arr);
			if (canEat(arr, n, m, k)) {
				System.out.println("#" + test + " Possible");
			} else {
				System.out.println("#" + test + " Impossible");
			}
		}
	}

	static boolean canEat(int[] arr, int n, int m, int k) {
		// System.out.println("n = " + n);
		// System.out.println("m = " + m);
		// System.out.println("k = " + k);
		// System.out.println(Arrays.toString(arr));
		int boong = 0;
		int time = 1;
		int index = 0;
        if(arr[0] == 0) {
			return false;
		}
		while (time <= arr[arr.length - 1]) {
			if (time % m == 0) {
				// System.out.println(time + "초가 되었으므로 " + k + "개 생성완료!");
				boong += k;
			}

			if (arr[index] == time) {
				// System.out.println(arr[index] + "초에 오시기로한 손님이 오셨다! 남은 붕어빵 개수 = " + boong);
				if (boong <= 0) {
					return false;
				} else {
					boong--;
				}
				index++;
			}
			time++;
		}
		return true;
	}
}
