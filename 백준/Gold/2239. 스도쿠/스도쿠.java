import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[][] sudoku = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();
    static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < 9; i++) {
            String[] str = sc.nextLine().split("");
            for (int j = 0; j < str.length; j++) {
                sudoku[i][j] = Integer.parseInt(str[j]);
                if (sudoku[i][j] == 0) {
                    blanks.add(new int[] {i, j});
                }
            }
        }

        bt(0);
        print();  // 첫 번째 답 출력
        
        sc.close();
    }

    public static void bt(int idx) {
        if (found) return;  // 이미 답을 찾았으면 종료
        
        if (idx == blanks.size()) {
            found = true;  // 첫 번째 답을 찾았으므로 종료
            return;
        }

        int[] pos = blanks.get(idx);
        int x = pos[0];
        int y = pos[1];
        
        List<Integer> possibleList = findPossibility(x, y);
        
        // 사전순이므로 1부터 9까지 순서대로 시도
        for (int num : possibleList) {
            sudoku[x][y] = num;
            bt(idx + 1);
            
            if (found) return;  // 답을 찾았으면 즉시 리턴
            
            sudoku[x][y] = 0;  // 백트래킹
        }
    }

    public static List<Integer> findPossibility(int x, int y) {
        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);
        possible[0] = false;  // 0은 사용 불가

        // 같은 행 체크
        for (int i = 0; i < 9; i++) {
            possible[sudoku[x][i]] = false;
        }

        // 같은 열 체크
        for (int i = 0; i < 9; i++) {
            possible[sudoku[i][y]] = false;
        }

        // 3x3 박스 체크
        int boxX = (x / 3) * 3;
        int boxY = (y / 3) * 3;
        for (int i = boxX; i < boxX + 3; i++) {
            for (int j = boxY; j < boxY + 3; j++) {
                possible[sudoku[i][j]] = false;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (possible[i]) {
                list.add(i);
            }
        }
        return list;
    }

    public static void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]);
            }
            System.out.println();
        }
    }
}