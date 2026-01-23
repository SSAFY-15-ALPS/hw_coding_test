package D3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class card_counting_4047 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();

		Map<Character, Integer> patternIndex = new HashMap<>();
		patternIndex.put('S', 0);
		patternIndex.put('D', 1);
		patternIndex.put('H', 2);
		patternIndex.put('C', 3);

		for (int test_case = 1; test_case <= T; test_case++) {
			String line = sc.nextLine();
			boolean[][] cards = new boolean[4][13];

			boolean isError = false;

			int[] answer = new int[] {13, 13, 13, 13};

			for (int i = 0; i < line.length(); i += 3) {
				char pattern = line.charAt(i);
				int num = Integer.parseInt(line.substring(i + 1, i + 3));
				int index = patternIndex.get(pattern);
				if (cards[index][num - 1]) {
					isError = true;
					break;
				}
				cards[index][num - 1] = true;
				answer[index] -= 1;
			}
			if (isError) {
				System.out.println("#" + test_case + " ERROR");
			} else {
				System.out.println(
					"#" + test_case + " " + answer[0] + " " + answer[1] + " " + answer[2] + " " + answer[3]);
			}
		}
		sc.close();
	}
}
