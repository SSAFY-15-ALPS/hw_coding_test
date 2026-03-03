import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		int left = 0;
		int sum = 0;

		int count = 0;

		for (int right = 0; right < n; right++) {
			sum += arr[right];
			while (sum >= m) {
				if (sum == m) {
					count++;
				}
				sum -= arr[left++];
			}
		}
		System.out.println(count);
	}
}
