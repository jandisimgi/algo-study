package 그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class BOJ_9205_맥주마시면서걸어가기 {
	static Node[] nodes; // 좌표 정보 담는 배열
	static boolean[] visited;
	static int N;

	// 좌표 저장 클래스 정의
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int distance(Node other) {
			return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
		}
	}

	// 페스티벌 도착 여부만 판단
	public static boolean bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(0);
		visited[0] = true;

		while (!queue.isEmpty()) {
			int curr = queue.poll();

			if (curr == N + 1)
				return true;

			for (int next = 0; next < N + 2; next++) {
				if (!visited[next] && nodes[curr].distance(nodes[next]) <= 1000) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());

			nodes = new Node[N + 2];
			visited = new boolean[N + 2];

			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				nodes[i] = new Node(x, y);
			}

			System.out.println(bfs() ? "happy" : "sad");
		} // tc
	}// main
}// class
