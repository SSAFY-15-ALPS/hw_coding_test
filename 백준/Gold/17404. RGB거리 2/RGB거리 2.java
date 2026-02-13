import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            arr[i][2] = sc.nextInt();
        }
        
        int answer = 99999999;
        for (int j = 0; j < 3; j++) {
            int[][] dp = new int[n][3];
            Arrays.fill(dp[0], 1001);
            if (j == 0) {
                dp[0][0] = arr[0][0];
            } else if (j == 1) {
                dp[0][1] = arr[0][1];
            } else {
                dp[0][2] = arr[0][2];
            }

            for (int i = 1; i < n; i++) {
                dp[i][0] = arr[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = arr[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = arr[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
                if (i == n-1) {
                    if (j == 0) {
                        dp[i][0] = 999999;
                    } else if (j == 1) {
                        dp[i][1] = 999999;
                    } else {
                        dp[i][2] = 999999;
                    }

                }

            }

//            for (int i = 0; i < n; i++) {
//                System.out.println(Arrays.toString(dp[i]));
//            }

            int min = Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
            answer = Math.min(answer, min);
        }
        System.out.println(answer);

        sc.close();
    }

}