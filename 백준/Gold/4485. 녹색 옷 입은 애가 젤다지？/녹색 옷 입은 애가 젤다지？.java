import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static class Edge implements Comparable<Edge> {
		int x;
		int y;
		int value;

		@Override
		public int compareTo(Edge edge) {
			return value - edge.value;
		}

		public Edge(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"x=" + x +
				", y=" + y +
				", value=" + value +
				'}';
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int problem = 1;
		while (true) {
			int n = sc.nextInt();
			if (n == 0)
				break;
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			solve(problem++, n, map);
		}

		sc.close();
	}

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void solve(int problem, int n, int[][] map) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		int[][] dist = new int[n][n];
		boolean[][] visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		dist[0][0] = map[0][0];

		pq.offer(new Edge(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int x = edge.x;
			int y = edge.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;
				if (visited[nx][ny])
					continue;
				if (dist[x][y] + map[nx][ny] > dist[nx][ny])
					continue;
				visited[nx][ny] = true;
				dist[nx][ny] = dist[x][y] + map[nx][ny];
				pq.offer(new Edge(nx, ny, dist[nx][ny]));
			}
		}
		System.out.println("Problem " + problem + ": " + dist[n - 1][n - 1]);
	}

}