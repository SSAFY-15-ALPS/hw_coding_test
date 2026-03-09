import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class DP {
	int count;
	List<Integer> path;

	public DP(int count, List<Integer> path) {
		this.count = count;
		this.path = path;
	}

	@Override
	public String toString() {
		return "DP{" +
			"count=" + count +
			", path=" + path +
			'}';
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		Map<Integer, DP> map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			map.computeIfAbsent(i, k -> new DP(0, new ArrayList<>()));
		}
		map.get(1).path.add(1);

		for (int i = 2; i <= n; i++) {
			DP dp = map.get(i);
			if (i % 2 == 0 && i % 3 == 0) { // 6의 배수
				if (map.get(i - 1).count <= map.get(i / 2).count && map.get(i - 1).count <= map.get(i / 3).count) {
					dp.count = map.get(i - 1).count + 1;
					List<Integer> list = new ArrayList<>(List.copyOf(map.get(i - 1).path));
					list.add(i);
					dp.path = list;
				} else {
					if (map.get(i / 2).count <= map.get(i / 3).count) {
						dp.count = map.get(i / 2).count + 1;
						List<Integer> list = new ArrayList<>(List.copyOf(map.get(i / 2).path));
						list.add(i);
						dp.path = list;
					} else {
						dp.count = map.get(i / 3).count + 1;
						List<Integer> list = new ArrayList<>(List.copyOf(map.get(i / 3).path));
						list.add(i);
						dp.path = list;
					}
				}
			} else if (i % 2 == 0) { // 2의 배수
				if (map.get(i - 1).count >= map.get(i / 2).count) {
					dp.count = map.get(i / 2).count + 1;
					List<Integer> list = new ArrayList<>(List.copyOf(map.get(i / 2).path));
					list.add(i);
					dp.path = list;
				} else {
					dp.count = map.get(i - 1).count + 1;
					List<Integer> list = new ArrayList<>(List.copyOf(map.get(i - 1).path));
					list.add(i);
					dp.path = list;
				}
			} else if (i % 3 == 0) { // 3의 배수
				if (map.get(i - 1).count >= map.get(i / 3).count) {
					dp.count = map.get(i / 3).count + 1;
					List<Integer> list = new ArrayList<>(List.copyOf(map.get(i / 3).path));
					list.add(i);
					dp.path = list;
				} else {
					dp.count = map.get(i - 1).count + 1;
					List<Integer> list = new ArrayList<>(List.copyOf(map.get(i - 1).path));
					list.add(i);
					dp.path = list;
				}
			} else { // 둘다 아닐때
				dp.count = map.get(i - 1).count + 1;
				List<Integer> list = new ArrayList<>(List.copyOf(map.get(i - 1).path));
				list.add(i);
				dp.path = list;
			}
		}

		List<Integer> answer = map.get(n).path;
		System.out.println(map.get(n).count);
		for (int i = answer.size() - 1; i >= 0; i--) {
			System.out.print(answer.get(i));
			if(i != 0) {
				System.out.print(" ");
			}
		}

		sc.close();
	}

}
