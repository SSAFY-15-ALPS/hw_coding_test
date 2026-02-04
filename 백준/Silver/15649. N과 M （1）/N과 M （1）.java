import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    int n = Integer.parseInt(str.split(" ")[0]);
    int m = Integer.parseInt(str.split(" ")[1]);
    nm(0,n,m);
    System.out.println(answer.toString());
  }

  static boolean[] b = new boolean[10];
  static int[] a = new int[10];
  static StringBuilder answer = new StringBuilder();

  static void nm(int index, int n, int m) {
    if(index == m) {
      for(int i = 0;i<m;i++) {
        answer.append(a[i]);
        if(i != m-1) answer.append(" ");
      } answer.append("\n");
      return;
    }
    for (int i=1; i<=n; i++) {
      if (b[i]) continue;
      b[i] = true;
      a[index] = i;
      nm(index+1, n, m);
      b[i] = false;
    }
  }
}