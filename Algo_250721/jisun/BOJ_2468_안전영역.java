package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2468_안전영역 {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] safe;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// map 데이터 입력
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 지역 중 최대높이 구하기
		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (max < map[r][c]) {
					max = map[r][c];
				}
			}
		}

		// 1부터 최대높이 전까지 물에 잠기지 않는 안전한 영역 배열 생성
		safe = new int[max];

		for (int i = 0; i < max; i++) {
			visited = new boolean[N][N];
			int cnt = 0;

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] <= i) {
						map[r][c] = -1;
					}
				}
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && map[r][c] != -1) {
						bfs(r, c);
						cnt++;
					}

				}
			}
			safe[i] = cnt;
		}

		Arrays.sort(safe);
		System.out.println(safe[max - 1]);

	}// main

	private static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { sr, sc });
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = curr[0] + dr[d];
				int nc = curr[1] + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (visited[nr][nc])
					continue;
				if (map[nr][nc] == -1)
					continue;

				q.offer(new int[] { nr, nc });
				visited[nr][nc] = true;
			}
		}
	}// bfs

}// class
