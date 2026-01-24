package BaekJoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class S1_2667 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] area = new int[n][n];
		for (int i = 0; i < n; i++) {
			area[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		boolean[][] visited = new boolean[n][n];

		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		List<Integer> answerList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (area[i][j] == 0)
					continue;
				if (visited[i][j])
					continue;

				Queue<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[] {i, j});
				int count = 0;
				while (!queue.isEmpty()) {
					int[] current = queue.poll();
					int x = current[0];
					int y = current[1];
					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if (nx < 0 || nx >= n || ny < 0 || ny >= n)
							continue;
						if (visited[nx][ny])
							continue;
						if (area[nx][ny] == 1) {
							queue.offer(new int[] {nx, ny});
							visited[nx][ny] = true;
							count++;
						}
					}
				}
				answerList.add(count);
			}
		}

		System.out.println(answerList.size());
		for(int answer : answerList.stream().sorted().collect(Collectors.toList())) {
			if(answer != 0) {
				System.out.println(answer);
			} else {
				System.out.println(1);
			}
		}
	}
}
