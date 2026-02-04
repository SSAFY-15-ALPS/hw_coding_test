import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String find = br.readLine();

		int[] table = generateTable(find);
		KMP(s, find, table);
	}

	static void KMP(String s, String find, int[] table) {
		int index = 0;
		int count = 0;
		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			while (index > 0 && s.charAt(i) != find.charAt(index)) {
				index = table[index - 1];
			}

			if (s.charAt(i) == find.charAt(index)) {
				if (index == find.length() - 1) {
					answer.add(i - find.length() + 2);
					count++;
					index = table[index];
				} else {
					index += 1;
				}
			}
		}
		if (count == 0) {
			System.out.println(0);
		} else {
			System.out.println(count);
			for(int ans : answer) {
				System.out.println(ans);
			}
		}
	}

	static int[] generateTable(String find) {
		int m = find.length();
		int[] table = new int[m];
		int j = 0;

		for (int i = 1; i < m; i++) {
			while (j > 0 && find.charAt(i) != find.charAt(j)) {
				j = table[j - 1];
			}
			if (find.charAt(i) == find.charAt(j)) {
				j++;
				table[i] = j;
			}
		}

		return table;
	}
}
