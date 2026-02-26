import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int left = 0;
		int right = n - 1;
		int min = Integer.MAX_VALUE;
		int min_left = -1;
		int min_right = -1;

		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == 0) {
				System.out.println(arr[left] + " " + arr[right]);
				return;
			} else if (sum > 0) {
				if (min >= sum) {
					min = sum;
					min_left = arr[left];
					min_right = arr[right];
				}
				right--;
			} else {
				if (min >= Math.abs(sum)) {
					min = Math.abs(sum);
					min_left = arr[left];
					min_right = arr[right];
				}
				left++;
			}
		}
		System.out.println(min_left + " " + min_right);

	}
}
