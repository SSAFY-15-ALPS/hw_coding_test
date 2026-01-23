package Algorithm_track_level_중;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class D2_파리퇴치3 {
	public static void main(String[] args) throws IOException {
		System.setIn(Files.newInputStream(Paths.get("src/Algorithm_track_level_중/SW Expert Academy in1.txt")));

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();

			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			int answer = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int sum1 = arr[i][j];
					int sum2 = arr[i][j];

					for (int k = 1; k < M; k++) {
						if (i - k >= 0) {
							sum1 += arr[i - k][j];
						}
						if (i + k < N) {
							sum1 += arr[i + k][j];
						}
						if (j - k >= 0) {
							sum1 += arr[i][j - k];
						}
						if (j + k < N) {
							sum1 += arr[i][j + k];
						}
					}

					for (int k = 1; k < M; k++) {
						if (i - k >= 0 && j - k >= 0) {
							sum2 += arr[i-k][j-k];
						}
						if (i - k >= 0 && j + k < N) {
							sum2 += arr[i-k][j+k];
						}
						if (i + k < N && j - k >= 0) {
							sum2 += arr[i+k][j-k];
						}
						if (i + k < N && j + k < N) {
							sum2 += arr[i+k][j+k];
						}
					}

					int sum = Math.max(sum1, sum2);
					answer = Math.max(sum, answer);
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
		sc.close();
	}
}