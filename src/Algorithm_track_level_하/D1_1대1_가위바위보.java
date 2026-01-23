package Algorithm_track_level_하;

import java.util.Arrays;
import java.util.Scanner;

public class D1_1대1_가위바위보 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] T = sc.nextLine().split(" ");
		int A = Integer.parseInt(T[0]);
		int B = Integer.parseInt(T[1]);
		switch (A) {
			case 1: {
				switch (B) {
					case 2:
						System.out.println("B");
						break;
					case 3:
						System.out.println("A");
						break;
				}
			}
			case 2: { // A가 주먹
				switch (B) {
					case 1: // B가 가위
						System.out.println("A");
						break;
					case 3: // B가 보
						System.out.println("B");
						break;
				}
			}
			case 3: { // A가 보
				switch (B) {
					case 1: // B가 가위
						System.out.println("B");
						break;
					case 2:
						System.out.println("A");
						break;
				}
			}
		}
	}
}
