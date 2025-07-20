import java.io.*;
import java.util.*;

public class BOJ_2638_치즈 {
    private static int N, M;
    private static int[][] air;
    private static boolean[][] map;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static void markOutsideAir() {
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visit[0][0] = true;
        air[0][0] = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (!visit[nx][ny] && !map[nx][ny]) {
                        visit[nx][ny] = true;
                        air[nx][ny] = -1; //외부
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
                        if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                            if (air[nx][ny] == -1) cnt++; 
                        }
                    }
                    air[i][j] = cnt;
                }
            }
        }
    }

    private static int removeCheese() {
        int out = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] && air[i][j] >= 2) {
                    map[i][j] = false;
                    out++;
                }
                air[i][j] = 0; 
            }
        }
        return out;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];
        air = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        int time = 0;
        while (true) {
            markOutsideAir(); 
            checkAir();    
            int removed = removeCheese();
            time++;
            if (removed == 0) break; 
        }

        System.out.println(time - 1); 
    }
}
