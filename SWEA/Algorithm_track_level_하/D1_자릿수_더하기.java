package Algorithm_track_level_하;

import java.util.Scanner;

public class D1_자릿수_더하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] T = sc.next().split("");
		int sum = 0;
		for (String s : T) {
			sum += Integer.parseInt(s);
		}
		System.out.println(sum);
	}
}