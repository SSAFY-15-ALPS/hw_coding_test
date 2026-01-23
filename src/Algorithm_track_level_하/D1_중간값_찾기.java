package Algorithm_track_level_하;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class D1_중간값_찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		List<Integer> arr = new ArrayList<>();
		for (int test_case = 1; test_case <= T; test_case++) {
			int a = sc.nextInt();
			arr.add(a);
		}
		arr = arr.stream().sorted().collect(Collectors.toList());
		System.out.println(arr.get(T / 2));
	}
}
