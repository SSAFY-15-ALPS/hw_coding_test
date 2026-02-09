import java.util.Scanner;

public class Main {
    static char[][] board;
    static int r, c;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[] alphabet = new boolean[26];  // 알파벳 방문 체크
    static int max = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            board[i] = sc.nextLine().toCharArray();
        }

        alphabet[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        
        System.out.println(max);
        sc.close();
    }

    static void dfs(int x, int y, int depth) {
        max = Math.max(max, depth);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
            
            int idx = board[nx][ny] - 'A';
            if (alphabet[idx]) continue;  // O(1) 체크!
            
            alphabet[idx] = true;
            dfs(nx, ny, depth + 1);
            alphabet[idx] = false;  // O(1) 복원!
        }
    }
}