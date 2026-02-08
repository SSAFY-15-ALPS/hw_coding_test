import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		boolean[] arr = new boolean[n + 1];
		for (int i = 0; i < n; i++) {
			// if(sc.nextInt() == 1) {
			// 	arr[i] = true;
			// } else {
			// 	arr[i] = false;
			// }
			arr[i + 1] = sc.nextInt() == 1;
		}

		int m = sc.nextInt();
		// print(arr);
		// System.out.println();
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			if (x == 1) {
				// System.out.println("남자!!" + y);
				manChange(arr, y);
				// print(arr);
				// System.out.println();
			} else {
				// System.out.println("여자!!" + y);
				womanChange(arr, y);
				// print(arr);
				// System.out.println();
			}
		}
		print(arr);
	}

	static void manChange(boolean[] arr, int y) {
		for (int i = y; i < arr.length; i += y) {
			arr[i] = !arr[i];
		}
	}

	static void womanChange(boolean[] arr, int y) {
		arr[y] = !arr[y];
		int index = 1;
		while (true) {
			if (y - index < 1 || y + index >= arr.length) {
				break;
			}
			// System.out.println("y - index = " + (y - index));
			// System.out.println("y + index = " + (y + index));
			if (arr[y - index] != arr[y + index]) {
				break;
			}
			arr[y - index] = !arr[y - index];
			arr[y + index] = !arr[y + index];
			index++;
		}

	}

	static void print(boolean[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i])
				System.out.print(1);
			else
				System.out.print(0);
			if (i != arr.length - 1)
				System.out.print(" ");
			if(i % 20 == 0) {
				System.out.println();
			}
		}
	}
}
