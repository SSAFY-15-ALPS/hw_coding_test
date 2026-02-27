import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class Position implements Comparable<Position> {
	int[] position;
	int value;
	int dist;

	public Position(int[] position, int value) {
		this.position = position;
		this.value = value;
		this.dist = 0;
	}

	@Override
	public int compareTo(Position position) {
		if (this.dist == position.dist) {
				if (this.position[0] == position.position[0]) {
					return this.position[1] - position.position[1];
				} else {
					return this.position[0] - position.position[0];
				}
			}
		else {
			return this.dist - position.dist;
		}
	}

	@Override
	public String toString() {
		return "Position{" +
			"position=" + Arrays.toString(position) +
			", value=" + value +
			", dist=" + dist +
			'}';
	}

}

public class Main {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] map = new int[n][n];
		int[] baby_shark = new int[2];
		int baby_shark_size = 2;
		int baby_shark_eat = 0;
		int answer = 0;

		List<Position> fish = new ArrayList<>();

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					baby_shark[0] = i;
					baby_shark[1] = j;
					map[i][j] = 0;
				} else if (map[i][j] != 0) {
					Position position = new Position(new int[] {i, j}, map[i][j]);
					fish.add(position);
				}
			}
		}

		Collections.sort(fish);

		while (true) {
			Position next = getNextFish(baby_shark_size, fish, baby_shark, map);
			if (next == null)
				break;
			answer += next.dist;
			baby_shark = next.position;
			map[next.position[0]][next.position[1]] = 0;
			fish.remove(next);
			// System.out.println("아기상어가 음식을 먹었습니다! 음식 위치 = " + Arrays.toString(next.position));
			baby_shark_eat++;
			if (baby_shark_eat == baby_shark_size) {
				// System.out.println("아기상어 진화!");
				baby_shark_eat = 0;
				baby_shark_size++;
				// System.out.println("아기상어 크기 = " + baby_shark_size);
			}
		}

		System.out.println(answer);

		sc.close();
	}

	static Position getNextFish(int baby_shark_size, List<Position> fishes, int[] baby_shark, int[][] map) {
		Position next = null;
		for (Position fish : fishes) {
			if (fish.value < baby_shark_size) {
				fish.dist = dist(fish.position, baby_shark, baby_shark_size, map);
				if (fish.dist == -1)
					continue;
				if (next == null || fish.compareTo(next) < 0) {
					next = fish;
				}
			}
		}
		return next;
	}

	static int dist(int[] pos, int[] baby_shark, int baby_shark_size, int[][] map) {
		boolean[][] visited = new boolean[map.length][map.length];
		Queue<int[]> queue = new ArrayDeque<>();

		visited[baby_shark[0]][baby_shark[1]] = true;
		queue.offer(new int[] {baby_shark[0], baby_shark[1], 0});

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];
			int d = current[2];

			if (x == pos[0] && y == pos[1]) {
				return d;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= map.length || ny < 0 || ny >= map.length)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] > baby_shark_size)
					continue;

				visited[nx][ny] = true;
				queue.offer(new int[] {nx, ny, d + 1});
			}
		}

		return -1; // 도달 불가
	}
}
