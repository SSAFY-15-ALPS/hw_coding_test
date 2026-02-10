import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        
        if (n == 1) {
            System.out.println(arr[0][0]);
        } else {
            find(arr, n);
        }
        sc.close();
    }
    
    static void find(int[][] arr, int n) {
        int[][] a = new int[n][2];  // [행 인덱스, 현재 값]
        
        // 각 열의 마지막 행으로 초기화
        for (int i = 0; i < n; i++) {
            a[i][0] = n - 1;
            a[i][1] = arr[n - 1][i];
        }
        
        int count = 0;
        while (count < n) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            
            // 최댓값 찾기
            for (int i = 0; i < n; i++) {
                if (max < a[i][1]) {  // < 로 변경
                    max = a[i][1];
                    index = i;
                }
            }
            
            count++;
            if (count == n) {
                System.out.println(a[index][1]);
                break;
            }
            
            // 선택된 열의 위쪽 행으로 이동
            if (a[index][0] > 0) {
                a[index][0] -= 1;
                a[index][1] = arr[a[index][0]][index];
            } else {
                a[index][1] = Integer.MIN_VALUE;  // 더 이상 값이 없으면 최솟값으로
            }
        }
    }
}