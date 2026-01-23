package codeUp.Clang;

import java.util.Scanner;

public class SpiderWeb {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] space = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				space[i][j] = sc.nextInt();
			}
		}

		int[] dx = {0, 0, 1, 1, 1, -1, -1, -1};
		int[] dy = {1, -1, 0, -1, 1, 0, 1, -1};

		int max = 0;
		int max_x = -1;
		int max_y = -1;

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int count = 1;
				if(space[i][j] == 0) {
					for(int k = 0; k < dx.length; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						while(nx >= 0 && nx < n && ny >= 0 && ny < n) {
							if(space[nx][ny] == 0) {
								count+=1;
							} else {
								if(nx+dx[k] < n && nx + dx[k] >= 0 && ny + dy[k] < n && ny + dy[k] >= 0) {
									if(space[nx+dx[k]][ny + dy[k]] == 1) {
										break;
									}
								}
							}
							nx += dx[k];
							ny += dy[k];
						}
					}
				}
				if(count > max) {
					max = count;
					max_x = i;
					max_y = j;
				}
			}
		}
		System.out.println(max);
		System.out.println(max_x + "," + max_y);
	}
}

// 0 0 0 0 0 0 0 0 0 0
// 0 1 0 1 0 1 0 1 0 1
// 0 1 0 1 0 1 0 1 0 1
// 0 0 1 0 1 0 1 0 1 0
// 0 0 0 1 0 1 0 1 0 1
// 0 1 0 0 0 1 0 0 0 1
// 0 0 1 0 0 0 0 0 1 0
// 0 1 0 0 0 1 0 0 0 0
// 0 1 1 1 1 1 1 1 1 1
// 0 0 0 0 0 0 0 0 0 0