import java.io.*;
import java.util.*;

public class Main {

	private static int N, L, R;
	private static int[][] population;
	private static int[] dx = {0, 1, -1, 0};
	private static int[] dy = {1, 0, 0, -1};

	private static boolean changePopulation() {
		boolean moved = false;
		boolean[][] visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j]) continue;

				Queue<int[]> q = new ArrayDeque<>();
				List<int[]> group = new ArrayList<>();

				q.offer(new int[] {i, j});
				group.add(new int[] {i, j});
				visit[i][j] = true;

				int sum = population[i][j];

				while (!q.isEmpty()) {
					int[] out = q.poll();
					int x = out[0], y = out[1];

					for (int d = 0; d < 4; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];

						if (0 <= nx && nx < N && 0 <= ny && ny < N) {
							if (!visit[nx][ny]) {
								int diff = Math.abs(population[x][y] - population[nx][ny]);
								if (L <= diff && diff <= R) {
									q.offer(new int[] {nx, ny});
									group.add(new int[] {nx, ny});
									visit[nx][ny] = true;
									sum += population[nx][ny];
								}
							}
						}
					}
				}

				if (group.size() > 1) {
					moved = true;
					int avg = sum / group.size();
					for (int[] pos : group) {
						population[pos[0]][pos[1]] = avg;
					}
				}
			}
		}
		return moved;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		population = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				population[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) {
			if (!changePopulation()) break;
			day++;
		}
		System.out.println(day);
	}
}
