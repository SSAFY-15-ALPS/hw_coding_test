
import java.util.*;

class Main {

    static int[] dx = { 1, -1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dh = { 0, 0, 0, 0, -1, 1 };

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int h = sc.nextInt();

        int[][][] tomatos = new int[h][n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        int unRipeCount = 0;

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tomatos[k][i][j] = sc.nextInt();
                    if (tomatos[k][i][j] == 1) {
                        queue.add(new int[] { k, i, j });
                    }
                    if (tomatos[k][i][j] == 0) {
                        unRipeCount++;
                    }
                }
            }
        }

        if (unRipeCount == 0) {
            System.out.println(0);
            return;
        }

        int day = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int x = current[1];
                int y = current[2];
                int height = current[0];
                for (int j = 0; j < 6; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    int nh = height + dh[j];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m || nh < 0 || nh >= h)
                        continue;
                    if (tomatos[nh][nx][ny] != 0)
                        continue;
                    tomatos[nh][nx][ny] = 1;
                    unRipeCount--;
                    queue.add(new int[] { nh,nx, ny});
                }
            }
            day++;
        }

        if (unRipeCount > 0) {
            System.out.println(-1);
        } else {
            System.out.println(day - 1);
        }

        sc.close();
    }

}