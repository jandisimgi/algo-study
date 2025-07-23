package remind;

import java.util.*;

public class BOJ_2304_창고다각형 {
	static class Node implements Comparable<Node> {
		int position;
		int height;

		Node(int position, int height) {
			this.position = position;
			this.height = height;
		}

		@Override
		public int compareTo(Node other) {
			return this.position - other.position;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<Node> nodes = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int pos = sc.nextInt();
			int height = sc.nextInt();
			nodes.add(new Node(pos, height));
		}

		// 위치 기준 정렬
		Collections.sort(nodes);

		// 가장 높은 기둥 찾기
		int maxIndex = 0;
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			if (nodes.get(i).height > maxHeight) {
				maxHeight = nodes.get(i).height;
				maxIndex = i;
			}
		}

		int area = 0;

		// 왼쪽에서 maxIndex까지 면적 계산
		int leftMax = nodes.get(0).height;
		int leftPos = nodes.get(0).position;
		for (int i = 1; i <= maxIndex; i++) {
			Node curr = nodes.get(i);
			if (curr.height >= leftMax) {
				area += (curr.position - leftPos) * leftMax;
				leftMax = curr.height;
				leftPos = curr.position;
			}
		}

		int rightMax = nodes.get(n - 1).height;
		int rightPos = nodes.get(n - 1).position;
		for (int i = n - 2; i >= maxIndex; i--) {
			Node curr = nodes.get(i);
			if (curr.height >= rightMax) {
				area += (rightPos - curr.position) * rightMax;
				rightMax = curr.height;
				rightPos = curr.position;
			}
		}

		area += maxHeight;

		System.out.println(area);
		sc.close();
	}
}
