import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    for (int t = 0; t < T; t++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());

      int[][] land = new int[M][N];
      boolean[][] visited = new boolean[M][N];
      
      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        land[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]++;
      }

      int cnt = 0;
      for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
          if (!visited[i][j] && land[i][j] == 1) {
            cnt++;
            
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{i, j});
            visited[i][j] = true;

            while (!q.isEmpty()) {
              int[] curr = q.poll();
              int[] dr = {-1, 1, 0, 0};
              int[] dc = {0, 0, -1, 1};

              for (int d = 0; d < 4; d++) {
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];

                if (nr >= 0 && nc >= 0 && nr < M && nc < N && !visited[nr][nc] && land[nr][nc] == 1) {
                  q.add(new int[]{nr, nc});
                  visited[nr][nc] = true;
                }
              }
            }
          }
        }
      }

      System.out.println(cnt);
    }
  }
}
