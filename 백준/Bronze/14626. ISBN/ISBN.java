import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String isbn = sc.next();
        
        int starIdx = isbn.indexOf('*');
        
        for (int d = 0; d <= 9; d++) {
            int sum = 0;
            for (int i = 0; i < 13; i++) {
                int digit = (i == starIdx) ? d : (isbn.charAt(i) - '0');
                int weight = (i % 2 == 0) ? 1 : 3;
                sum += digit * weight;
            }
            if (sum % 10 == 0) {
                System.out.println(d);
                return;
            }
        }
    }
}