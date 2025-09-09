import java.util.*;
import java.io.*;

public class BOJ_2636_치즈 {
	
	private static boolean[][] map;
	private static int[][] air;
	private static int N, M;
	private static int[] dx = {0,0,1,-1};
	private static int[] dy = {1,-1, 0,0};
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		air = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1;
			}
		}
	}
	
	private static void solution(){
		int time = 0, lastCheese =0;
		while(true) {
			markOutsideAir();
			checkAir();
			int result = removeCheese();
			if(result != 0) {
				lastCheese = result;
			}else break;
			
			time++;
		}
		
		System.out.println(time);
		System.out.println(lastCheese);
	}
	
	private static void markOutsideAir() {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visit[0][0] = true;
        air[0][0] = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (0<= nx && nx < N && 0<= ny && ny < M) {
                    if (!visit[nx][ny] && !map[nx][ny]) {
                        visit[nx][ny] = true;
                        air[nx][ny] = -1; 
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
	
	private static void checkAir() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];
                        if (0<=nx && nx < N && 0<= ny  && ny < M) {
                            if (air[nx][ny] == -1) cnt++; 
                        }
                    }
                    air[i][j] = cnt;
                }
            }
        }
    }

    private static int removeCheese() {
    	int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] && air[i][j] >= 1) {
                    map[i][j] = false;
                    cnt++;
                }
                air[i][j] = 0; 
            }
        }
        return cnt;
    }
}
