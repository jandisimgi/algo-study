import java.io.*;
import java.util.*;

public class BOJ_2583_영역구하기 {
    static int M, N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); 
        N = Integer.parseInt(st.nextToken()); 
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        List<Integer> areas = new ArrayList<>();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 0 && !visited[y][x]) {
                    areas.add(bfs(y, x));
                }
            }
        }

        Collections.sort(areas);
        System.out.println(areas.size());
        for (int a : areas) System.out.print(a + " ");
    }

    static int bfs(int sy, int sx) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int y = cur[0], x = cur[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (ny < 0 || nx < 0 || ny >= M || nx >= N) continue;
                if (!visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
