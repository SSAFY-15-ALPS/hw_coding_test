package codeUp.Clang;

import java.util.Scanner;

public class MinerGame {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] mine = new int[n][n];
		int x = -1;
		int y = -1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				mine[i][j] = sc.nextInt();
				if (mine[i][j] == 0) {
					x = i;
					y = j;
				}
			}
		}

		int[] dx = {-1, 1, 0, 0, 1, 1, -1, -1};
		int[] dy = {0, 0, -1, 1, 1, -1, 1, -1};
		int m = sc.nextInt();
		int count = 0;

		for (int i = 0; i < m; i++) {
			String pos = sc.next();
			int po = -1;
			switch (pos) {
				case "U":
					po = 0;
					break;
				case "D":
					po = 1;
					break;
				case "L":
					po = 2;
					break;
				case "R":
					po = 3;
					break;
				case "X":
					for (int k = 0; k < dx.length; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
							if (mine[nx][ny] != 0) {
								count++;
								mine[nx][ny] = 0;
							}
						}
					}
					break;
			}

			if (!pos.equals("X")) {
				int nx = x + dx[po];
				int ny = y + dy[po];

				if (nx >= 0 && nx < n && ny < n && ny >= 0) {
					if (mine[nx][ny] <= 1) {
						if (mine[nx][ny] == 1) {
							count++;
						}
						mine[nx][ny] = 0;

						x = nx;
						y = ny;
					} else {
						mine[nx][ny] -= 1;
					}
				}
			}
		}
		System.out.println("광부 위치 : (" + x + "," + y + ")");
		System.out.println("부순 암석 개수 : " + count);
	}
}

// 10
// 1 1 1 1 1 1 1 1 1 1
// 2 1 3 2 1 3 2 1 2 2
// 1 1 0 2 1 2 1 1 1 2
// 1 2 1 1 1 1 1 1 2 1
// 3 1 2 2 1 2 2 1 2 1
// 1 1 1 2 3 2 1 1 1 1
// 2 1 1 2 1 1 3 2 2 1
// 1 2 2 1 1 1 1 3 2 1
// 1 1 1 2 1 2 2 2 1 2
// 3 2 1 3 2 1 3 2 1 3
// 10
// D
// D
// R
// D
// D
// X
// D
// X
// U
// L