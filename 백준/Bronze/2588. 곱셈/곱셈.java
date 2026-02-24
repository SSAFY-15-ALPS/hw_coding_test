import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		String str = sc.next();
		
		int a1 = a * (str.charAt(2) - '0');
		int a2 = a * (str.charAt(1) - '0');
		int a3 = a * (str.charAt(0) - '0');
		
		
		System.out.println(a1);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(a * Integer.parseInt(str));
	}
}
