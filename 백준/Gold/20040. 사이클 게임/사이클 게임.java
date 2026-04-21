import java.util.*;

public class Main {
    static int[] parent;

    // find: 루트 찾기 + 경로 압축
    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    // union: 두 집합 합치기
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 점 개수
        int m = sc.nextInt(); // 차례 수

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int result = 0; // 최초 사이클 발생 차례
        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

            if (find(u) == find(v)) {
                result = i; // 최초 사이클 발생
                break;
            } else {
                union(u, v);
            }
        }

        System.out.println(result); // 0이면 사이클 없음
    }
}