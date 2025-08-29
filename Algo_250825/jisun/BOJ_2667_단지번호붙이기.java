
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_2667_단지번호붙이기 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 2][N + 2];
		for (int r = 1; r < N + 1; r++) {
			String line = br.readLine();
			for (int c = 1; c < N + 1; c++) {
				map[r][c] = line.charAt(c - 1) - '0';
			}
		}

		visited = new boolean[N + 2][N + 2];
		List<Integer> result = new ArrayList<>();

		for (int r = 1; r < N + 1; r++) {
			for (int c = 1; c < N + 1; c++) {
				if (map[r][c] == 1 && !visited[r][c]) {
					result.add(bfs(r, c));
				}
			}
		}

		Collections.sort(result);
		System.out.println(result.size());
		for (int count : result) {
			System.out.println(count);
		}

	}// main

	static int bfs(int sr, int sc) {
		Queue<int[]> q = new LinkedList<>();
		visited[sr][sc] = true;
		q.offer(new int[] { sr, sc });

		int cnt = 1;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
					cnt++;
				}
			}
		}
		
		return cnt;

	}// bfs

}// class
