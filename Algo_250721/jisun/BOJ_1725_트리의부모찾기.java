package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1725_트리의부모찾기 {
	static int[] parent; // 각 노드의 부모를 저장할 배열
	static List<Integer>[] adj; // 인접리스트
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		visited = new boolean[N + 1];
		parent = new int[N + 1];

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj[s].add(e);
			adj[e].add(s);
		}

		bfs(1);

		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}

	}// main

	static void bfs(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[v] = true;
		q.offer(v);

		while (!q.isEmpty()) {
			int curr = q.poll();

			for (int next : adj[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					//아래 두 코드 순서 상관없는데 '부모를 지정한 뒤에 큐에 넣는게' 의미상으로 맞음  
					parent[next] = curr;
					q.offer(next);

				}
			}
		}
	}
}// class
