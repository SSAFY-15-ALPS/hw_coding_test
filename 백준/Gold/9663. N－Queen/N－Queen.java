import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        
        nQueen(0);
        
        System.out.println(count);
        sc.close();
    }

    static void nQueen(int depth) {
        if (depth == N) {
            count++;
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (possibility(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    static boolean possibility(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[col] == arr[i]) {
                return false;
            }
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}