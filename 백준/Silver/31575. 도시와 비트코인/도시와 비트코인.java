import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 가로
        int M = sc.nextInt(); // 세로
        int[][] grid = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        boolean[][] dp = new boolean[M][N];
        dp[0][0] = true; // 출발점은 항상 1

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) continue; // 건물: 지나갈 수 없음
                if (i > 0) dp[i][j] |= dp[i - 1][j];
                if (j > 0) dp[i][j] |= dp[i][j - 1];
            }
        }

        System.out.println(dp[M - 1][N - 1] ? "Yes" : "No");
    }
}