import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int sum = 0;
		int minLen = Integer.MAX_VALUE;

		for (int right = 0; right < n; right++) {
			sum += arr[right];

			while (sum >= s) {
				minLen = Math.min(minLen, right - left + 1);
				sum -= arr[left++];
			}
		}

		System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
	}

}
