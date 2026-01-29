import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(1);
		} else {
			int[][] time = new int[n][2];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				time[i][0] = Integer.parseInt(st.nextToken());
				time[i][1] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(time, (o1, o2) -> {
				if (o1[1] != o2[1]) {
					return o1[1] - o2[1]; // 끝나는 시간 오름차순
				}
				return o1[0] - o2[0]; // 시작 시간 오름차순
			});

//			for (int[] row : time) {
//				for (int num : row) {
//					System.out.print(num + " ");
//				}
//				System.out.println();
//			}

			int count = 1;
			int end_time = time[0][1];

			for (int i = 1; i < n; i++) {
				if (time[i][0] >= end_time) {
					count++;
					end_time = time[i][1];
				}
			}

			System.out.println(count);
		}
	}
}
