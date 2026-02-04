import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

		boolean[] visited = new boolean[n];
		List<Integer> output = new ArrayList<>();

		perm(visited, output, 0, m, -1);

		sc.close();
	}

	static void perm(boolean[] visited, List<Integer> output, int depth, int m, int prev) {
		if (depth == m) {
			for (int i = 0; i < output.size(); i++) {
				System.out.print(output.get(i));
				if (i != output.size() - 1) {
					System.out.print(" ");
				}
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i] && prev < i) {
				visited[i] = true;
				output.add(i + 1);
				perm(visited, output, depth + 1, m, i);
				output.remove(output.size() - 1);
				visited[i] = false;
			}
		}
	}

}
