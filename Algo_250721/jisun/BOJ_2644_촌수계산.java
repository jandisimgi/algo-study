package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2644_촌수계산 {
	static int N, M, v1, v2;
	static List<Integer>[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			adj[s].add(e);
			adj[e].add(s);
		}

		visited = new boolean[N + 1];
		System.out.println(bfs(v1, v2));

	}// main

	static int bfs(int v1, int v2) {
		int[] dist = new int[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		visited[v1] = true;
		q.add(v1);

		int depth = 0;

		while (!q.isEmpty()) {
			int curr = q.poll();

			if (curr == v2)
				return dist[curr];

			for (int next : adj[curr]) {
				if (!visited[next]) {
					visited[next] = true;
					dist[next] = dist[curr] + 1;
					q.add(next);
				}
			}
		}
		return -1;
	}// bfs
}// class
