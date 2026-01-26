

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		// 간선 추가
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(graph.get(i));
		}

		boolean[] visited = new boolean[n+1];
		dfs(graph, visited, v);
		System.out.println();

		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(v);

		boolean[] bfs_visited = new boolean[n+1];
		while(!queue.isEmpty()) {
			int node = queue.poll();
			System.out.print(node + " ");
			bfs_visited[node] = true;

			for(int i : graph.get(node)) {
				if(!bfs_visited[i]) {
					queue.offer(i);
					bfs_visited[i] = true;
				}
			}

		}

	}

	public static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node) {
		visited[node] = true;
		System.out.print(node + " ");

		for(int i : graph.get(node)) {
			if(!visited[i]) {
				dfs(graph, visited, i);
			}
		}
	}
}
