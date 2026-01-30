import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        long N = Long.parseLong(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long[] sizes = new long[6];
        for (int i = 0; i < 6; i++) {
            sizes[i] = Long.parseLong(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        long T = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        
        // 1. 티셔츠 묶음 최소 개수
        long tshirtBundles = 0;
        for (int i = 0; i < 6; i++) {
            tshirtBundles += (sizes[i] + T - 1) / T; // 올림 나눗셈
        }
        
        // 2. 펜 묶음 최대 개수 & 낱개
        long penBundles = N / P;
        long penSingles = N % P;
        
        StringBuilder sb = new StringBuilder();
        sb.append(tshirtBundles).append("\n");
        sb.append(penBundles).append(" ").append(penSingles);
        
        System.out.print(sb.toString());
    }
}
