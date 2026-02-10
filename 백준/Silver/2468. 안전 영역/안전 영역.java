import java.util.*;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                min = Math.min(min, arr[i][j]);
                max = Math.max(max, arr[i][j]);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        int answer = 0;
        
        if(min == max) {
            answer = 1;
        }
        
        while (min != max) {
            boolean[][] visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] == min) {
                        arr[i][j] = 0;
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != 0 && !visited[i][j]) {
                        count++;
                        queue.offer(new int[] { i, j });
                        visited[i][j] = true;
                        while (!queue.isEmpty()) {
                            int[] current = queue.poll();
                            int x = current[0];
                            int y = current[1];
                            for (int k = 0; k < 4; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                if (nx < 0 || nx >= n || ny < 0 || ny >= n)
                                    continue;
                                if (visited[nx][ny])
                                    continue;
                                if (arr[nx][ny] == 0)
                                    continue;
                                queue.offer(new int[] { nx, ny });
                                visited[nx][ny] = true;
                            }
                        }
                    }
                }
            }
            answer = Math.max(answer, count);
            min += 1;
        }
        System.out.println(answer);
        sc.close();
    }

}