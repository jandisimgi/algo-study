import java.io.*;
import java.util.*;

public class BOJ_1600_말이되고픈원숭이 {
	private static int K, N, M;
	private static boolean[][] board;

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1, -2, -2, -1, -1, 1, 1, 2, 2};
		int[] dy = {1, -1, 0, 0, -1, 1, -2, 2, -2, 2, -1, 1};
		boolean[][][] visit = new boolean[N][M][K + 1];

		q.offer(new int[]{0, 0, K});
		visit[0][0][K] = true;

		int step = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				int[] out = q.poll();
				int x = out[0], y = out[1], cnt = out[2];

				if (x == N - 1 && y == M - 1) return step;

				int end = (cnt > 0) ? 12 : 4;

				for (int d = 0; d < end; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if (board[nx][ny]) continue;

					if (d >= 4 && cnt > 0 && !visit[nx][ny][cnt - 1]) {
						visit[nx][ny][cnt - 1] = true;
						q.offer(new int[]{nx, ny, cnt - 1});
					} else if (d < 4 && !visit[nx][ny][cnt]) {
						visit[nx][ny][cnt] = true;
						q.offer(new int[]{nx, ny, cnt});
					}
				}
			}
			step++;
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로

		board = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken()) == 1;
			}
		}
		System.out.println(bfs());
	}
}