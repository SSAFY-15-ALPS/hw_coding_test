import java.io.*;
import java.util.*;

public class Main {

	static String[][] map;
	static int x;
	static int y;
	static List<int[]> result;
	static int Maxcount = 0;
	static boolean[][] visited;
	static int T;
	static int R;
	static int C;
	static int[] dx = { -1, 1, 0, 0, 0 };
	static int[] dy = { 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		T = Integer.parseInt(line[2]);

		map = new String[R][C];

		for (int i = 0; i < R; i++) {
			line = br.readLine().split("");
			for (int j = 0; j < line.length; j++) {
				map[i][j] = line[j];
				if (map[i][j].equals("G")) {
					x = i;
					y = j;
				}
			}
		}
		gahee(0, 0);
		System.out.println(Maxcount);
	}

	public static void gahee(int depth, int count) {
		if (depth == T) {
			Maxcount = Math.max(Maxcount, count);
			return;
		}
		for (int j = 0; j < 5; j++) {
			int nx = x + dx[j];
			int ny = y + dy[j];

			if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
				continue;
			}

			if (map[nx][ny].equals("#"))
				continue;

			int prevX = x;
			int prevY = y;

			x = nx;
			y = ny;

			if (map[x][y].equals("S")) {
				map[x][y] = ".";
				gahee(depth + 1, count + 1);
				map[x][y] = "S";
			} else {
				gahee(depth + 1, count);
			}
			x = prevX;
			y = prevY;
		}

	}

}