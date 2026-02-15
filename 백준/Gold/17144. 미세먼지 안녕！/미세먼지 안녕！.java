import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int t = sc.nextInt();
		int robot_s = -1;
		int robot_e = -1;
		int[][] map = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					robot_e = i;
					robot_s = i - 1;
				}
			}
		}

		for (int i = 0; i < t; i++) {
			map = dust(map);
			// printMap(map);
			cleanUp(map, robot_s);
			cleanDown(map, robot_e);
			// printMap(map);
		}
		System.out.println(sum(map));
	}

	static int[][] dust(int[][] map) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		int r = map.length;
		int c = map[0].length;

		int[][] temp = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == -1) {
					temp[i][j] = -1;
				} else if (map[i][j] != 0) {
					int count = 0;
					int spread = map[i][j] / 5;
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if (nx < 0 || nx >= r || ny < 0 || ny >= c)
							continue;
						if (map[nx][ny] == -1)
							continue;
						count++;
						temp[nx][ny] += spread;
					}
					temp[i][j] += map[i][j] - (spread * count);
				}
			}
		}

		return temp;
	}

	static void cleanUp(int[][] map, int s) {
		int[] dx = {0, -1, 0, 1};
		int[] dy = {1, 0, -1, 0};
		int x = s;
		int y = 1;
		int pos = 0;
		int prev = 0;
		while (x != s || y != 0) {
			int temp = map[x][y];
			map[x][y] = prev;
			int nx = x + dx[pos];
			int ny = y + dy[pos];
			if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
				pos = (pos + 1) % 4;
				prev = temp;
				x += dx[pos];
				y += dy[pos];
				continue;
			}
			prev = temp;
			x = nx;
			y = ny;
		}

	}

	static void cleanDown(int[][] map, int s) {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int x = s;
		int y = 1;
		int pos = 0;
		int prev = 0;
		while (x != s || y != 0) {
			int temp = map[x][y];
			map[x][y] = prev;
			int nx = x + dx[pos];
			int ny = y + dy[pos];
			if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
				pos = (pos + 1) % 4;
				prev = temp;
				x += dx[pos];
				y += dy[pos];
				continue;
			}
			prev = temp;
			x = nx;
			y = ny;
		}
	}

	static int sum(int[][] map) {
		int sum = 2;
		for (int[] ints : map) {
			for (int j = 0; j < map[0].length; j++) {
				sum += ints[j];
			}
		}
		return sum;
	}

	static void printMap(int[][] map) {
		for (int[] ints : map) {
			System.out.println(Arrays.toString(ints));
		}
		System.out.println("-----------------------------------");
	}
}
