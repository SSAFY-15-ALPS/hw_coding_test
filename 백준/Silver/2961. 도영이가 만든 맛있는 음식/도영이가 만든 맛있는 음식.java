import java.io.*;
import java.util.*;

public class Main {
//	static List<Integer> output_acidity_list = new ArrayList<>();
//	static List<Integer> output_acerbity_list = new ArrayList<>();
	static int[] output_acidity;
	static int[] output_acerbity;
	static boolean[] visited;
	static int[] acerbity;
	static int[] acidity;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		acidity = new int[n];
		acerbity = new int[n];
		output_acerbity = new int[n];
		output_acidity = new int[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			acidity[i] = sc.nextInt();
			acerbity[i] = sc.nextInt();
		}
		for (int i = 1; i <= n; i++) {
			bt(visited, 0, n, i);
		}
		System.out.println(min);
		sc.close();
	}

	static void bt(boolean[] visited, int depth, int n, int m) {
		if (depth == m) {
			int aci = 1;
			int ace = 0;
			for (int a : output_acidity) {
				if (a != 0) {
					aci *= a;
				}
			}
			for (int a : output_acerbity) {
				ace += a;
			}
			min = Math.min(Math.abs(aci - ace), min);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i])
				continue;
			output_acidity[depth] = acidity[i];
			output_acerbity[depth] = acerbity[i];
			visited[i] = true;
			bt(visited, depth + 1, n, m);
			visited[i] = false;
		}
	}

}