import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for (int test = 1; test <= t; test++) {
			int n = sc.nextInt();

			int[][] map = new int[n][n];

			Set<Integer> taste = new HashSet<>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					taste.add(map[i][j]);
				}
			}

			int max = 1;
			for (int i : taste) {
				int a = bfs(map, n, i);
				max = Math.max(a, max);
			}
			System.out.println("#" + test + " " + max);
		}

		sc.close();
	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static int bfs(int[][] map, int n, int taste) {
		boolean[][] visited = new boolean[n][n];
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Queue<int[]> queue = new ArrayDeque<>();
				if (visited[i][j])
					continue;
				visited[i][j] = true;
				if (map[i][j] <= taste) {
					continue;
				}

				queue.offer(new int[] {i, j});
				count++;
				while (!queue.isEmpty()) {
					int[] cur = queue.poll();
					int x = cur[0];
					int y = cur[1];
					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if (nx < 0 || nx >= n || ny < 0 || ny >= n)
							continue;
						if (visited[nx][ny])
							continue;
						visited[nx][ny] = true;
						if (map[nx][ny] <= taste) {
							continue;
						}
						queue.offer(new int[] {nx, ny});
					}
				}
			}
		}

		return count;
	}

}