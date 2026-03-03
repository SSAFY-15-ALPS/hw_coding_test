import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String word = sc.next();
		int n = word.length();
		String[] arr = new String[n];

		for (int i = n - 1; i >= 0; i--) {
			arr[i] = word.substring(i, n);
		}

		Arrays.sort(arr);
		for(int i = 0; i < n; i++) {
			System.out.println(arr[i]);
		}
		sc.close();
	}
}