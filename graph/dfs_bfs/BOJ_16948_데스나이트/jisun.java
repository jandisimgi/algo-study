package 그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class BOJ_16948_데스나이트 {
	static int[] dr = { -2, -2, 0, 0, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -1, 1 };
	static boolean[][] visited;
	static int[][] dist;
	static int N;
	static int r1, c1, r2, c2;

	static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { r, c });
		visited[r][c] = true;
		dist[r][c] = 0;

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int cr = curr[0];
			int cc = curr[1];

			// 종료 조건
			if (cr == r2 && cc == c2) {
				System.out.println(dist[cr][cc]);
				return;
			}

			for (int d = 0; d < 6; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (!visited[nr][nc]) {
					visited[nr][nc] = true;
					dist[nr][nc] = dist[cr][cc] + 1;
					queue.offer(new int[] { nr, nc });
				}
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		visited = new boolean[N][N];
		dist = new int[N][N];

		bfs(r1, c1);
	}
}
