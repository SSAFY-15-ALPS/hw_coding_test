import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			Integer x = Integer.parseInt(br.readLine());
			if(x == 0) {
				if(minHeap.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(minHeap.poll());
				}
			} else {
				minHeap.add(x);
			}
		}

	}
}
