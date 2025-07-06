import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static Set<Integer> cheese;
	static Map<Integer, Integer> insideAir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cheese = new HashSet<>();
		insideAir = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheese.add(i * M + j);
				}
			}
		}

		checkInside();
		int ans = getTime();
		System.out.println(ans);
	}// main();

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static void checkInside() {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		List<Integer> list = new ArrayList<>();
		boolean[] visited = new boolean[N * M];
		int curr, r, c, newR, newC, insideNum = 10;
		boolean inside;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i * M + j] || map[i][j] == 1) {
					continue;
				}

				que.add(i * M + j);
				inside = true;
				list.clear();

				while (!que.isEmpty()) {
					curr = que.poll();
					r = curr / M;
					c = curr % M;

					if (visited[curr]) {
						continue;
					}

					visited[curr] = true;
					list.add(curr);

					if (r == 0 || r == N - 1 || c == 0 || c == M - 1) {
						inside = false;
					}

					for (int d = 0; d < 4; d++) {
						newR = r + dr[d];
						newC = c + dc[d];
						if (newR >= 0 && newR < N && newC >= 0 && newC < M && map[newR][newC] == 0) {
							que.add(newR * M + newC);
						}
					}
				}

				if (inside) {
					for (int in : list) {
						map[in / M][in % M] = insideNum;
					}
					insideAir.put(insideNum++, list.get(0));
				}
			}
		}
	}

	static int getTime() {
		Set<Integer> prevCheese = new HashSet<>();
		Set<Integer> nowAir = new HashSet<>();
		Set<Integer> nowOutside = new HashSet<>();
		Set<Integer> couldBeOutside = new HashSet<>();

		int r, c, newR, newC, airCnt, time = 0;
		while (!cheese.isEmpty()) {
			time++;
			prevCheese = new HashSet<>(cheese);
			nowAir.clear();
			nowOutside.clear();

			for (int curr : prevCheese) {
				r = curr / M;
				c = curr % M;
				airCnt = 0;
				couldBeOutside.clear();
				
				for (int d = 0; d < 4; d++) {
					newR = r + dr[d];
					newC = c + dc[d];
					if(newR >= 0 && newR < N && newC >= 0 && newC < M) {
						if (map[newR][newC] == 0) {
							airCnt++;
						} else if (map[newR][newC] >= 10) {
							couldBeOutside.add(map[newR][newC]);
						}
					}
				}
				if (airCnt >= 2) {
					nowAir.add(curr);
					for(int air : couldBeOutside) {
						nowOutside.add(air);
					}
				}
			}
			
			for(int ch : nowAir) {
				cheese.remove(ch);
				map[ch / M][ch % M] = 0;
			}
			for (int air : nowOutside) {
				changeOutside(air);
			}
		}
		return time;
	}

	static void changeOutside(int air) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(insideAir.get(air));

		int curr, r, c, newR, newC;
		while (!que.isEmpty()) {
			curr = que.poll();
			r = curr / M;
			c = curr % M;

			if (map[r][c] == 0) {
				continue;
			}
			map[r][c] = 0;

			for (int d = 0; d < 4; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if (newR >= 0 && newR < N && newC >= 0 && newC < M && map[newR][newC] == air) {
					que.add(newR * M + newC);
				}
			}
		}
	}
}
