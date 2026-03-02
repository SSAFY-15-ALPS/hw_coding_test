import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 집의 개수
		int c = sc.nextInt(); // 공유기 개수
		int[] homes = new int[n];
		for (int i = 0; i < n; i++) {
			homes[i] = sc.nextInt();
		}

		Arrays.sort(homes);

		int left = 1;
		int right = homes[n - 1] - homes[0];

		while (left <= right) {
			int mid = (right + left) / 2;
			if (canInstall(homes, mid, n, c)) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(right);
	}

	static boolean canInstall(int[] homes, int distance, int n, int c) {
		int pos = 0;
		int prevInstalled = homes[pos];
		int count = 1;
		pos++;
		while (pos < n) {
			if (count == c) {
				return true;
			}
			if (homes[pos] >= prevInstalled + distance) {
				count++;
				prevInstalled = homes[pos];
			}
			pos++;

		}

		return count >= c;
	}
}
