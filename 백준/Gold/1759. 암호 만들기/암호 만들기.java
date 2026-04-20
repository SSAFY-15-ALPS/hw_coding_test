import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int l;
	static int c;
	static String[] str;
	static boolean[] visited;
	static List<String> output = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		l = sc.nextInt();
		c = sc.nextInt();

		str = new String[c];
		visited = new boolean[c];

		for (int i = 0; i < c; i++) {
			str[i] = sc.next();
		}

		Arrays.sort(str);

		bt(0, -1);

		sc.close();
	}

	public static void bt(int depth, int prev) {
		if (depth == l) {
			if (contains()) {
				for (int i = 0; i < l; i++) {
					System.out.print(output.get(i));
				}
				System.out.println();
			}
			return;
		}

		for (int i = 0; i < c; i++) {
			if (visited[i] || prev >= i)
				continue;
			visited[i] = true;
			output.add(str[i]);
			bt(depth + 1, i);
			output.remove(output.size() - 1);
			visited[i] = false;
		}

	}

	public static boolean contains() {
		int c1 = 0;
		int c2 = 0;

		for (String s : output) {
			if (s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u"))
				c1++;
			else
				c2++;
		}

		return c1 >= 1 && c2 >= 2;
	}

}