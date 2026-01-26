import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < t; test_case++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int[][] farm = new int[m][n];
			boolean[][] visited = new boolean[m][n];

			for (int i = 0; i < k; i++) {
				int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				int x = line[0];
				int y = line[1];
				farm[x][y] = 1;
			}

			Queue<int[]> queue = new ArrayDeque<>();

			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };

			int count = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (farm[i][j] == 1 && !visited[i][j]) {
						count++;
						queue.offer(new int[] { i, j });
						while (!queue.isEmpty()) {
							int[] current = queue.poll();
							int x = current[0];
							int y = current[1];
							visited[x][y] = true;
							for (int q = 0; q < 4; q++) {
								int nx = x + dx[q];
								int ny = y + dy[q];
								if (nx < 0 || nx >= m || ny < 0 || ny >= n)
									continue;
								if (visited[nx][ny])
									continue;
								if (farm[nx][ny] == 1) {
									queue.offer(new int[] { nx, ny });
									visited[nx][ny] = true;
								}
							}
						}
					}

				}
			}
			System.out.println(count);
		}
	}
}
