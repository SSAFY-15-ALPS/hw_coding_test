import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static int[] d;
  public static int go(int a) {
    if(a == 1) {
      return 0;
    }
    if(d[a] > 0) {
      return d[a];
    }
    d[a] = go(a-1)+1;
    if(a % 2 == 0) {
      int temp = go(a / 2) + 1;
      if(d[a] > temp) {
        d[a] = temp;
      }
    }
    if(a % 3 == 0) {
      int temp = go(a / 3) + 1;
      if(d[a] > temp) {
        d[a] = temp;
      }
    }
    return d[a];
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int a = Integer.parseInt(br.readLine());
    d = new int[a+1];
    System.out.println(go(a));
  }
}