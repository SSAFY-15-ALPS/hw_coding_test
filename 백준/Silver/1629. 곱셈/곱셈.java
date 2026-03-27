import java.util.Scanner;

public class Main {
    public static long pow(long a, long b, long c) {
        if (b == 0) return 1 % c;
        if (b % 2 == 0) {
            long half = pow(a, b / 2, c);
            return (half * half) % c;
        } else {
            return (pow(a, b - 1, c) * a) % c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        System.out.println(pow(a, b, c));
    }
}