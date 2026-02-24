import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test = 0; test < T; test++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int[] a_arr = new int[a];
			int[] b_arr = new int[b];
			for (int i = 0; i < a; i++) {
				a_arr[i] = sc.nextInt();
			}
			for (int i = 0; i < b; i++) {
				b_arr[i] = sc.nextInt();
			}

			int aIndex = a - 1;
			int bIndex = b - 1;

			Arrays.sort(a_arr);
			Arrays.sort(b_arr);
			int sum = 0;
			while (aIndex >= 0 && bIndex >= 0) {
				if (a_arr[aIndex] > b_arr[bIndex]) {
					sum += (bIndex + 1);
					aIndex--;
				} else {
					bIndex--;
				}
			}

			System.out.println(sum);

		}
	}
}
