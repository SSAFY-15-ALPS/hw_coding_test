import java.util.*;

class Main {
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static class Point {
        int x, y, dist, wall;

        public Point(int x, int y, int dist, int wall) {
            super();
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.wall = wall;
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        visited = new boolean[n][m][2];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < line.length(); j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        
        
        System.out.println(BFS());

        sc.close();
    }

    static int BFS() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;
                if (arr[nx][ny] == 0 && !visited[nx][ny][cur.wall]) {
                    visited[nx][ny][cur.wall] = true;
                    queue.offer(new Point(nx, ny, cur.dist + 1, cur.wall));
                }

                if (arr[nx][ny] == 1 && cur.wall == 0 && !visited[nx][ny][1]) {
                    visited[nx][ny][1] = true;
                    queue.offer(new Point(nx, ny, cur.dist + 1, 1));
                }
            }

        }

        return -1;
    }
}