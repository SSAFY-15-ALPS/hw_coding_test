import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[][] papers = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				papers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check(papers, new int[] { 0, 0 }, n);
		System.out.println(white);
		System.out.println(blue);
	}

	static int blue;
	static int white;

	public static void check(int[][] papers, int[] start, int n) {
		if (n == 1) {
			if (papers[start[0]][start[1]] == 1) {
				blue++;
			} else {
				white++;
			}
		} else {
			if (isAllBlue(papers, start, n)) {
				blue++;
			} else if (isAllWhite(papers, start, n)) {
				white++;
			} else {
				n /= 2;
				check(papers, new int[] { start[0] + n, start[1] }, n);
				check(papers, new int[] { start[0], start[1] + n }, n);
				check(papers, new int[] { start[0] + n, start[1] + n }, n);
				check(papers, new int[] { start[0], start[1] }, n);
			}
		}
	}

	public static boolean isAllBlue(int[][] papers, int[] start, int n) {
		int x = start[0];
		int y = start[1];
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (papers[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static boolean isAllWhite(int[][] papers, int[] start, int n) {
		int x = start[0];
		int y = start[1];
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (papers[i][j] == 1)
					return false;
			}
		}
		return true;
	}

}
