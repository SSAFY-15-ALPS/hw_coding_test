import java.util.*;

class Main {

    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        int[][] map = new int[n][m];

        Queue<int[]> queue = new ArrayDeque<>();
        
        for(int i = 0; i <n; i++) {
            for(int j = 0; j <m; j++) {
                map[i][j]= sc.nextInt(); 
            }
        }
        
        queue.add(new int[] { r, c });
        
        

        int count = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            boolean hasPlace = false;
            int x = current[0];
            int y = current[1];

            // 현재칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (map[x][y] == 0) {
                count++;
                map[x][y] = 2; // 벽이 아니고 청소가 되었으면 2로 선언.
            }

            // 주변 4칸 중 천소 되지 않은 빈칸을 찾기
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    hasPlace = true;
                    break;
                }
            }

            if (hasPlace) {
                // 청소되지 않은 빈칸이 있는 경우
                if (d == 0) {
                    d = 3;
                } else {
                    d -= 1;
                }
                
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (map[nx][ny] == 0) {
                    queue.add(new int[] { nx, ny });
                } else {
                    queue.add(new int[] { x, y });
                }
            } else {
                // 청소되지 않은 빈칸이 없는 경우
                int nx = x - dx[d];
                int ny = y - dy[d];
                // 만약 바라보는 방향의 뒤쪽 칸이 벽인경우
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    break;
                }
                if (map[nx][ny] == 1) {
                    break;
                }
                queue.add(new int[] { nx, ny });
            }

        }

        System.out.println(count);

        sc.close();
    }

}