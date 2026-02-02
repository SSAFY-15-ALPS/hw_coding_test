

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int index;
	int cost;

	public Node(int index, int cost) {
		super();
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static ArrayList<Node>[] graph;

	public static int Dijkstra(int n, int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[n + 1];
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		dist[start] = 0;
		pq.offer(new Node(start, 0));

		while (!pq.isEmpty()) {
			int cur = pq.poll().index;
			if (visited[cur])
				continue;
			visited[cur] = true;

			for (Node next : graph[cur]) {
				if (dist[next.index] > dist[cur] + next.cost) {
					dist[next.index] = dist[cur] + next.cost;

					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}

		return dist[end];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		graph = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[v].add(new Node(w, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		System.out.println(Dijkstra(n, start, end));
	}
}
