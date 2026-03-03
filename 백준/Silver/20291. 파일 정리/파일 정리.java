import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String a = sc.next().split("\\.")[1];
			map.merge(a, 1, Integer::sum);
		}
		Object[] list = map.keySet().toArray();
		Arrays.sort(list);
		for (Object str : list) {
			System.out.println(str + " " + map.get((String)str));
		}
		sc.close();
	}
}