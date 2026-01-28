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
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] dist = new int[2][100001];
		Arrays.fill(dist[0], 100001);
		Arrays.fill(dist[1], 1);

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(n);
		dist[0][n] = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if(cur == k) {
				break;
			}
			int minus = cur - 1;
			int plus = cur + 1;
			int tp = cur * 2;

			if (minus >= 0 && minus <= 100000) {
				if (dist[0][minus] == dist[0][cur] + 1) {
					dist[1][minus] += 1;
					queue.offer(minus);
				} else if(dist[0][minus] > dist[0][cur] + 1) {
					dist[0][minus] = dist[0][cur] + 1;
					dist[1][minus] = 1;
					queue.offer(minus);
				}
			}
			
			if (plus >= 0 && plus <= 100000) {
				if (dist[0][plus] == dist[0][cur] + 1) {
					dist[1][plus] += 1;
					queue.offer(plus);
				} else if(dist[0][plus] > dist[0][cur] + 1) {
					dist[0][plus] = dist[0][cur] + 1;
					dist[1][plus] = 1;
					queue.offer(plus);
				}
			}
			
			if (tp >= 0 && tp <= 100000) {
				if (dist[0][tp] == dist[0][cur]) {
					dist[1][tp] += 1;
					queue.offer(tp);
				} else if (dist[0][tp] > dist[0][cur]){
					dist[0][tp] = dist[0][cur];
					dist[1][tp] = 1;
					queue.offer(tp);
				}
			}
		}
		System.out.println(dist[0][k]);
	}

}
