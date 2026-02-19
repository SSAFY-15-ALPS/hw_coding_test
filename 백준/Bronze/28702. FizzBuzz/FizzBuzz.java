import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = new String[3];
        for (int i = 0; i < 3; i++) s[i] = sc.next();

        int base = -1;
        for (int i = 0; i < 3; i++) {
            if (s[i].matches("\\d+")) {
                base = Integer.parseInt(s[i]) - i;
                break;
            }
        }

        int n = base + 3;
        if      (n % 15 == 0) System.out.println("FizzBuzz");
        else if (n % 3  == 0) System.out.println("Fizz");
        else if (n % 5  == 0) System.out.println("Buzz");
        else                  System.out.println(n);
    }
}