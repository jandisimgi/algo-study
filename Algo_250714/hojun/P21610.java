package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P21610 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[][] A = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		
		int[] d = new int[M];
		int[] s = new int[M];
		for (int i = 0; i < M; i++) {
			d[i] = sc.nextInt();
			s[i] = sc.nextInt();
		}
		
		int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
		int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
		
		int[] diagR = {-1, -1, 1, 1};
        int[] diagC = {-1, 1, 1, -1};
		
		List<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{N - 1, 0});
        clouds.add(new int[]{N - 1, 1});
        clouds.add(new int[]{N - 2, 0});
        clouds.add(new int[]{N - 2, 1});
		
		for (int i = 0; i < M; i++) {
            int dir = d[i] - 1;
            int move = s[i];

            List<int[]> newClouds = new ArrayList<>();
            boolean[][] visited = new boolean[N][N];
            for (int[] cloud : clouds) {
                int nr = (cloud[0] + dr[dir] * move) % N;
                int nc = (cloud[1] + dc[dir] * move) % N;
                if (nr < 0) {
                	nr += N;
                }
                if (nc < 0) {
                	nc += N;
                }
                newClouds.add(new int[]{nr, nc});
            }

            for (int[] cloud : newClouds) {
                int r = cloud[0];
                int c = cloud[1];
                A[r][c]++;
                visited[r][c] = true;
            }

            for (int[] cloud : newClouds) {
                int r = cloud[0];
                int c = cloud[1];
                int count = 0;
                for (int dIdx = 0; dIdx < 4; dIdx++) {
                    int nr = r + diagR[dIdx];
                    int nc = c + diagC[dIdx];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (A[nr][nc] > 0) {
                        	count++;
                        }
                    }
                }
                A[r][c] += count;
            }

            clouds = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c] && A[r][c] >= 2) {
                        clouds.add(new int[]{r, c});
                        A[r][c] -= 2;
                    }
                }
            }
        }
										
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += A[i][j];
            }
        }
        System.out.println(sum);
	}
}
