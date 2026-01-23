package BaekJoon.Silver.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class S2_1260 {
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

		boolean[] dfsVisited = new boolean[n+1];
		Stack<Integer> stack = new Stack<>();
		stack.add(v);
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			if(dfsVisited[cur]) continue;
			System.out.println(cur);
			for (int i : graph.get(cur)) {
				if (!dfsVisited[i]) {
					dfsVisited[i] = true;
					stack.add(i);
				}
			}
		}
	}
}
