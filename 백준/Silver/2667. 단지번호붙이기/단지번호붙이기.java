import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] area = new int[n][n];

		for (int i = 0; i < n; i++) {
			area[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		boolean[][] visited = new boolean[n][n];
		ArrayList<Integer> houseCounts = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (area[i][j] == 1 && visited[i][j] == false) {
					int count = dfs(area, new int[] {i, j}, visited);
					houseCounts.add(count);
				}
			}
		}

		Collections.sort(houseCounts);
		System.out.println(houseCounts.size());
		for (int count : houseCounts) {
			System.out.println(count);
		}
	}

	public static int dfs(int[][] area, int[] node, boolean[][] visited) {
		int x = node[0];
		int y = node[1];
		int count = 1;
		visited[x][y] = true;
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= area.length || ny < 0 || ny >= area.length)
				continue;
			if (visited[nx][ny])
				continue;
			if (area[nx][ny] == 1) {
				count += dfs(area, new int[] {nx, ny}, visited);
			}
		}
		return count;
	}

	// 7
	// 0110100
	// 0110101
	// 1110101
	// 0000111
	// 0100000
	// 0111110
	// 0111000
}
