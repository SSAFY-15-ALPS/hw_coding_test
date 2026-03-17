import java.util.Scanner;

public class Main {

    static long[] len = new long[100];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        // S(k) 길이 사전 계산 (overflow 방지: 2e9 초과 시 고정)
        len[0] = 3;
        for (int i = 1; i < 100; i++) {
            len[i] = 2 * len[i - 1] + (i + 3);
            if (len[i] > 2_000_000_000L) {
                len[i] = 2_000_000_000L; // N 최대 1e9이므로 cap
            }
        }

        // N을 커버하는 최소 k 탐색
        int k = 0;
        while (len[k] < N) k++;

        System.out.println(solve(N, k));
    }

    static char solve(long N, int k) {
        // Base case: S(0) = "m o o"
        if (k == 0) {
            return N == 1 ? 'm' : 'o';
        }

        // 앞쪽 S(k-1) 구간
        if (N <= len[k - 1]) {
            return solve(N, k - 1);
        }
        N -= len[k - 1];

        // 중간 "m o...o" 구간: 길이 (k+3)
        //   -> 'm' 1개 + 'o' (k+2)개
        if (N == 1) return 'm';
        if (N <= k + 3) return 'o';
        N -= (k + 3);

        // 뒤쪽 S(k-1) 구간
        return solve(N, k - 1);
    }
}