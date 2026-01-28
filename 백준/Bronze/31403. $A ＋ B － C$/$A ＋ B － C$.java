import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        
        // A + B - C (수로 계산)
        System.out.println(A + B - C);
        
        // A + B - C (문자열로 계산)
        // A + B: 문자열 이어붙이기
        String AB = String.valueOf(A) + String.valueOf(B);
        // AB - C: 문자열을 수로 해석한 후 빼기
        System.out.println(Integer.parseInt(AB) - C);
        
        sc.close();
    }
}