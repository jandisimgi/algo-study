import java.io.*;
import java.util.*;

public class BOJ_16118_달빛_여우 {

	private static int N;
	private static List<Node>[] graph;
	private static PriorityQueue<Node> pq;
	private static int[] fox;
	private static int[][] wolf;

	private static class Node {
		int to;
		int cost;
		boolean isDouble;

		public Node(int to, int cost, boolean isDouble) {
			this.to = to;
			this.cost = cost;
			this.isDouble = isDouble;
		}
	}

	private static void moonlightFox() {
		pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		fox = new int[N + 1];
		Arrays.fill(fox, Integer.MAX_VALUE);

		pq.offer(new Node(1, 0, false));
		fox[1] = 0;

		while (!pq.isEmpty()) {
			Node out = pq.poll();
			int n = out.to;
			int c = out.cost;

			if (fox[n] < c) continue;

			for (Node node : graph[n]) {
				int newCost = c + node.cost;

				if (fox[node.to] > newCost) {
					fox[node.to] = newCost;
					pq.offer(new Node(node.to, newCost, false));
				}
			}
		}
	}

	private static void moonlightWolf() {
		pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
		wolf = new int[N + 1][2]; 
		for (int i = 1; i <= N; i++) {
			Arrays.fill(wolf[i], Integer.MAX_VALUE);
		}

		pq.offer(new Node(1, 0, false));
		wolf[1][0] = 0;

		while (!pq.isEmpty()) {
			Node out = pq.poll();
			int n = out.to;
			int c = out.cost;
			boolean d = out.isDouble;

			int state = d ? 1 : 0;
			if (wolf[n][state] < c) continue;

			for (Node next : graph[n]) {
				int nextCost = d ? c + next.cost * 2 : c + next.cost / 2;
				int nextState = d ? 0 : 1;

				if (wolf[next.to][nextState] > nextCost) {
					wolf[next.to][nextState] = nextCost;
					pq.offer(new Node(next.to, nextCost, !d));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()) * 2;

			graph[from].add(new Node(to, cost, false));
			graph[to].add(new Node(from, cost, false));
		}

		moonlightFox();
		moonlightWolf();

		int cnt = 0;
		for (int i = 2; i <= N; i++) {
			if (fox[i] < Math.min(wolf[i][0], wolf[i][1])) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}