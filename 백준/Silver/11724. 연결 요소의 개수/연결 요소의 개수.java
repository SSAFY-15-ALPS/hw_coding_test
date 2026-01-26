import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		if (m == 0) {
			System.out.println(n);
		} else {
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				graph.get(b).add(a);
			}

			boolean[] visited = new boolean[n + 1];

			int count = 0;
			for (int i = 1; i <= n; i++) {
				if (!visited[i]) {
					count++;
					dfs(i, graph, visited);
				}
			}
			System.out.println(count);
		}
	}

	public static void dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
		for (int n : graph.get(node)) {
			if (!visited[n]) {
				visited[n] = true;
				dfs(n, graph, visited);
			}
		}

	}
}
