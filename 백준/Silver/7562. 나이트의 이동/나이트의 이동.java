import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int[] dx = {-1, -2, -2, -1, 1, 1, 2, 2};
	static int[] dy = {2, 1, -1, -2, 2, -2, 1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();
		for (int test = 1; test <= t; test++) {
			int l = sc.nextInt();
			int startx = sc.nextInt();
			int starty = sc.nextInt();
			int endx = sc.nextInt();
			int endy = sc.nextInt();
			System.out.println(solve(l, startx, starty, endx, endy));
		}

		sc.close();
	}

	public static int solve(int l, int startx, int starty, int endx, int endy) {
		boolean[][] visited = new boolean[l][l];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startx, starty});
		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int x = cur[0];
				int y = cur[1];
				if (x == endx && y == endy) {
					return count;
				}
				for (int j = 0; j < 8; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if (nx < 0 || nx >= l || ny < 0 || ny >= l)
						continue;
					if (visited[nx][ny])
						continue;
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
			count++;
		}
		return -1;
	}

}