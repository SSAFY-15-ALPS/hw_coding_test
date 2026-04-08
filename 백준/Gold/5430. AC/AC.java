import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String commands = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arrLine = br.readLine();

            int[] arr;
            if (arrLine.equals("[]") || n == 0) {
                arr = new int[0];
            } else {
                arr = Arrays.stream(arrLine.substring(1, arrLine.length() - 1)
                        .split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            boolean reversed = false;
            int start = 0, end = arr.length - 1;
            boolean error = false;

            for (char cmd : commands.toCharArray()) {
                if (cmd == 'R') {
                    reversed = !reversed;
                } else if (cmd == 'D') {
                    if (start > end) {
                        error = true;
                        break;
                    }
                    if (!reversed) start++;
                    else end--;
                }
            }

            if (error) {
                System.out.println("error");
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("[");
                if (!reversed) {
                    for (int i = start; i <= end; i++) {
                        sb.append(arr[i]);
                        if (i != end) sb.append(",");
                    }
                } else {
                    for (int i = end; i >= start; i--) {
                        sb.append(arr[i]);
                        if (i != start) sb.append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb.toString());
            }
        }
    }
}