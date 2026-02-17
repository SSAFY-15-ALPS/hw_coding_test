import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken()); // 총감독관
        int c = Integer.parseInt(st.nextToken()); // 부감독관
        
        long answer = 0;
        
        for (int i = 0; i < n; i++) {
            answer += 1; // 총감독관 1명 필수
            
            int remain = a[i] - b; // 총감독관 배치 후 남은 인원
            if (remain > 0) {
                answer += (remain + c - 1) / c; // 올림 나눗셈
            }
        }
        
        System.out.println(answer);
    }
}