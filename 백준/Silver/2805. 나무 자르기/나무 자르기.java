import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);

    int N = sc.nextInt();
    int M = sc.nextInt();
    int[] arr = new int[N];
    long high = 0;
    for (int i = 0; i < N; i++) {
      arr[i] = sc.nextInt();
      if (high < arr[i]) {
        high = arr[i];
      }
    }

    high++;
    long low = 0;
    long mid = 0;

    while (low < high) {
      mid = (high + low) / 2;

      if (Count(arr, mid) < M) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    System.out.println(low - 1);
  }


  public static long Count(int[] arr, long val) {
    long count = 0;
    for (int i : arr) {
      if(i >= val)
      count += (i - val);
    }
    return count;
  }
}