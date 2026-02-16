import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n;
	static int l;
	static int r;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		l = sc.nextInt();
		r = sc.nextInt();

		map = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int count = 0;

		while (true) {
			boolean canMove = false;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						Queue<int[]> queue = new ArrayDeque<>();
						List<int[]> list = new ArrayList<>();
						int sum = 0;
						queue.offer(new int[] {i, j});
						visited[i][j] = true;
						while (!queue.isEmpty()) {
							int[] current = queue.poll();
							int x = current[0];
							int y = current[1];
							list.add(new int[] {x, y});
							sum += map[x][y];
							int p = map[x][y];
							for (int k = 0; k < 4; k++) {
								int nx = x + dx[k];
								int ny = y + dy[k];
								if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
									continue;
								}
								if (visited[nx][ny]) {
									continue;
								}
								int np = map[nx][ny];
								if (Math.abs(np - p) >= l && Math.abs(np - p) <= r) {
									queue.offer(new int[] {nx, ny});
									canMove = true;
									visited[nx][ny] = true;
								}
							}
						}

						if (list.size() > 1) {
							for (int[] index : list) {
								map[index[0]][index[1]] = sum / list.size();
								// visited[index[0]][index[1]] = true;
							}
						}
					}
				}
			}
			if (!canMove) {
				break;
			}
			count++;
		}
		System.out.println(count);
	}
}
