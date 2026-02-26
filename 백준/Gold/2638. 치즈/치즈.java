import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		Queue<int[]> queue = new ArrayDeque<>();

		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					queue.offer(new int[] {i, j});
				}
			}
		}

		int count = 0;
		while (!queue.isEmpty()) {
			// for (int i = 0; i < n; i++) {
			// 	System.out.println(Arrays.toString(map[i]));
			// }
			boolean[][] outAir = new boolean[n][m];

			Queue<int[]> air = new ArrayDeque<>();
			air.offer(new int[] {0, 0});
			while (!air.isEmpty()) {
				int[] cur = air.poll();
				int x = cur[0];
				int y = cur[1];

				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
						continue;
					}
					if (outAir[nx][ny]) {
						continue;
					}
					if (map[nx][ny] == 0) {
						air.offer(new int[] {nx, ny});
						outAir[nx][ny] = true;
					}
				}
			}
			// System.out.println("외부 공기");
			// for (int i = 0; i < n; i++) {
			// 	System.out.println(Arrays.toString(outAir[i]));
			// }

			count++;
			int size = queue.size();
			// System.out.println("size = " + size);
			List<int[]> meltList = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				int airCount = 0;
				int[] current = queue.poll();
				// System.out.println("치즈 위치 = " + Arrays.toString(current));
				int x = current[0];
				int y = current[1];
				boolean isMelt = false;
				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;
					if (outAir[nx][ny]) {
						if (airCount == 0) {
							// System.out.println("한면이 공기에 접촉했습니다!");
							airCount++;
						} else if (airCount == 1) {
							// System.out.println("두 면이 접촉되어있습니다! 녹아야 합니다");
							meltList.add(new int[] {x, y});
							isMelt = true;
							break;
						}
					}
				}
				if (!isMelt) {
					queue.offer(new int[] {x, y});
				}

			}
			for (int[] cur : meltList) {
				map[cur[0]][cur[1]] = 0;
			}
		}
		System.out.println(count);

		sc.close();
	}
}
