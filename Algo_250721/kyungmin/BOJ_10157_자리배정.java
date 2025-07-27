package remind;

import java.util.Scanner;

public class BOJ_10157_자리배정 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt(); // 가로
		int R = sc.nextInt(); // 세로
		int K = sc.nextInt(); // 대기번호

		// 좌석 배정 불가능한 경우
		if (K > C * R) {
			System.out.println(0);
			return;
		}

		// 상, 하, 좌, 우 방향 벡터
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		boolean[][] visited = new boolean[R + 1][C + 1];

		// 현재 방향 인덱스
		int dir = 0;

		// 시작 좌표
		int x = 1, y = 1;
		for (int i = 1; i < K; i++) {
			visited[y][x] = true;
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 방향 전환
			// 1. 범위를 벗어나거나
			// 2. 이미 방문한 경우
			if (nx < 1 || nx > C || ny < 1 || ny > R || visited[ny][nx]) {
				dir = (dir + 1) % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}

			x = nx;
			y = ny;
		}

		System.out.println(x + " " + y);
	}
}
