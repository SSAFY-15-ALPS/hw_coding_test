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

		int[] dist = new int[100001];
		Arrays.fill(dist, 100001);

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(n);
		dist[n] = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == k) {
				break;
			}
			int minus = cur - 1;
			int plus = cur + 1;
			int tp = cur * 2;
			if (minus >= 0 && minus <= 100000 && dist[minus] == 100001) {
				dist[minus] = Math.min(dist[minus], dist[cur] + 1);
				queue.offer(minus);
			}
			if (plus >= 0 && plus <= 100000 && dist[plus] == 100001) {
				dist[plus] = Math.min(dist[plus], dist[cur] + 1);
				queue.offer(plus);
			}
			if (tp >= 0 && tp <= 100000 && dist[tp] == 100001) {
				dist[tp] = Math.min(dist[tp], dist[cur] + 1);
				queue.offer(tp);
			}
		}
		System.out.println(dist[k]);
	}

}
