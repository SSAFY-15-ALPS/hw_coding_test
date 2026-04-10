import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int val;

	public Edge(int from, int to, int val) {
		this.from = from;
		this.to = to;
		this.val = val;
	}

	@Override
	public String toString() {
		return "Edge{" + "from=" + from + ", to=" + to + ", val=" + val + '}';
	}

	@Override
	public int compareTo(Edge edge) {
		return val - edge.val;
	}
}

public class Main {

	static int[] parent;
	static Edge[] edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());

		edges = new Edge[e];
		parent = new int[v + 1];

		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= v; i++) {
			parent[i] = i;
		}

		Arrays.sort(edges);

		int count = 0;
		int sum = 0;
		int max_val = Integer.MIN_VALUE;
		for (Edge edge : edges) {
			int from = edge.from;
			int to = edge.to;
			int val = edge.val;
			if (find(from) != find(to)) {
				count++;
				max_val = Integer.max(val, max_val);
				sum += val;
				union(from, to);
			}
			if (count == v - 1) {
				break;
			}
		}
		System.out.println(sum - max_val);

	}

	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		if (a != b) {
			parent[b] = a;
		}
	}
}