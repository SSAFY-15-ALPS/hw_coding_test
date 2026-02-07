

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int[][] sudoku = new int[9][9];
	static List<int[]> blank = new ArrayList<>();
	static Stack<int[]> blank_stack = new Stack<>();
	static int m = 0;
	static boolean found = false;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = sc.nextInt();
				if (sudoku[i][j] == 0) {
					m++;
					blank.add(new int[] {i, j});
					blank_stack.add(new int[] {i, j});
				}
			}
		}
		// System.out.println("빈칸 총 개수 : " + m);

		bt(0);
		sc.close();
	}

	public static void bt(int depth) {
		// System.out.println(depth + "번째 숫자");
		// System.out.println("현재 수도쿠 현황");
		// print();
		if (depth == m) {
			// System.out.println("---------------------------------");
			print();
			found = true;
			// System.out.println("빈칸을 모두 채웠습니다! 종료!!");
			return;
		}

		if (blank_stack.isEmpty()) {
			return;
		}

		int[] current = blank_stack.pop();
		int x = current[0];
		int y = current[1];
		// System.out.println(x + ", " + y + "지점");
		List<Integer> possibleList = findPossibility(x, y);
		if (possibleList.isEmpty()) {
			// System.out.println("빈칸에 들어갈 수를 찾지 못했습니다! 뒤로 돌아갑니다.");
			blank_stack.add(new int[] {x, y});
			return;
		}
		// System.out.println("가능한 수들 = " + possibleList);
		for (int i : possibleList) {
			// System.out.println(x + ", " + y + "에 " + i + "대입!");
			sudoku[x][y] = i;
			bt(depth + 1);
			if(found) {
				break;
			}
			sudoku[x][y] = 0;
		}
		if(found) return;
		blank_stack.add(new int[] {x, y});
	}

	public static List<Integer> findPossibility(int x, int y) {
		boolean[] possible = new boolean[10];
		Arrays.fill(possible, true);

		for (int i = 0; i < 9; i++) {
			if (i == x)
				continue;
			possible[sudoku[i][y]] = false;
		}

		for (int i = 0; i < 9; i++) {
			if (i == y)
				continue;
			possible[sudoku[x][i]] = false;
		}

		for (int i = (x / 3) * 3; i < (x / 3 + 1) * 3; i++) {
			for (int j = (y / 3) * 3; j < (y / 3 + 1) * 3; j++) {
				if (sudoku[i][j] == 0)
					continue;
				possible[sudoku[i][j]] = false;

			}
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < 10; i++) {
			if (possible[i]) {
				list.add(i);
			}
		}
		return list;
	}

	public static void print() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[i][j]);
				if (j != 8)
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
