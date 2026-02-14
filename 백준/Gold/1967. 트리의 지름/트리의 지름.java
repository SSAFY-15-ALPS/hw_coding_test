import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Node {
	int data;
	int value;

	public Node(int data, int value) {
		this.data = data;
		this.value = value;
	}
}

public class Main {

	static int n;
	static Map<Integer, ArrayList<Node>> graph = new HashMap<>();
	static int result = 0;  // 결과 값, 최대 지름

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		if(n ==1) {
			System.out.println(0);
			return;
		}

		if (n == 2) {  // 트리가 2개 노드만 있을 경우
			sc.nextInt();  // parent
			sc.nextInt();  // child
			int value = sc.nextInt();
			System.out.println(value);
			return;
		}

		// 트리 그래프 입력받기
		for (int i = 0; i < n - 1; i++) {
			int parent = sc.nextInt();
			int child = sc.nextInt();
			int value = sc.nextInt();
			graph.computeIfAbsent(parent, k -> new ArrayList<>()).add(new Node(child, value));
			graph.computeIfAbsent(child, k -> new ArrayList<>()).add(new Node(parent, value));  // 양방향 연결
		}

		// 1번 노드에서부터 DFS 시작 (트리에서 임의의 노드에서 시작해도 결과는 같음)
		DFS(1, -1);

		System.out.println(result);
	}

	// DFS 함수: 현재 노드, 부모 노드를 전달
	static int DFS(int node, int parent) {
		int max1 = 0;
		int max2 = 0;

		for (Node childNode : graph.get(node)) {
			if (childNode.data != parent) {
				int childDistance = DFS(childNode.data, node) + childNode.value;

				if (childDistance > max1) {
					max2 = max1;
					max1 = childDistance;
				} else if (childDistance > max2) {
					max2 = childDistance;
				}
			}
		}

		result = Math.max(result, max1 + max2);
		return max1;
	}
}
