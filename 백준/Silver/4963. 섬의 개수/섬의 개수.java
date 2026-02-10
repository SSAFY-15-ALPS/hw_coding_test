import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int[] dx = { -1, 1, 0, 0, 1, 1, -1, -1 };
        int[] dy = { 0, 0, -1, 1, 1, -1, -1, 1 };

        while (true) {
            int w = sc.nextInt();
            int h = sc.nextInt();
            if (w == 0 && h == 0) {
                break;
            }
            int[][] arr = new int[h][w];
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] == 1 && !visited[i][j]) {
                        queue.offer(new int[] { i, j });
                        visited[i][j] = true;
                        while (!queue.isEmpty()) {
                            int[] current = queue.poll();
                            int x = current[0];
                            int y = current[1];
                            for (int k = 0; k < 8; k++) {
                                int nx = x + dx[k];
                                int ny = y + dy[k];
                                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                                    continue;
                                if (visited[nx][ny])
                                    continue;
                                if (arr[nx][ny] == 1) {
                                    queue.offer(new int[] { nx, ny });
                                    visited[nx][ny]= true; 
                                }
                            }
                        }
                        count++;
                    }
                }
            }
            System.out.println(count);
        }

        sc.close();
    }

}