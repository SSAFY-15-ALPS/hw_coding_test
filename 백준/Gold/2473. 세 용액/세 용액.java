import java.util.Arrays;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        int first = 0, second = 0, third = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right];
                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    first = arr[i];
                    second = arr[left];
                    third = arr[right];
                }
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    System.out.println(first + " " + second + " " + third);
                    return;
                }
            }
        }
        System.out.println(first + " " + second + " " + third);
    }
}