import java.util.*;
import java.io.*;

public class Main{
  static int N, M, max;
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    int[][] lab = new int[N][M];
    
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        lab[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    max = 0;
    makeWall(0, -1, 0, lab);

    System.out.print(max);
  }

  static void makeWall(int r, int c, int depth, int[][] lab) {
    if (depth == 3) {
      virusSpread(lab);
      return;
    }
    
    for (int i = r; i < N; i++) {
      if (i != r) c = -1;
      for (int j = c+1; j < M; j++) {
        if (lab[i][j] != 0) continue;

        int[][] labCopy = new int[N][M];
        for (int k = 0; k < N; k++) {
          labCopy[k] = lab[k].clone();
        }
        
        labCopy[i][j] = 1;
        makeWall(i, j, depth+1, labCopy);
      }
    }
  }

  static void virusSpread(int[][] lab) {
    boolean[][] visited = new boolean[N][M];
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (lab[i][j] != 2) continue;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        q.add(new int[]{i, j});
        visited[i][j] = true;

        while(!q.isEmpty()) {
          int[] curr = q.poll();
          int r = curr[0];
          int c = curr[1];

          for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < N && nc < M && nr >= 0 && nc >= 0 && !visited[nr][nc] && lab[nr][nc] != 1) {
              lab[nr][nc] = 2;
              visited[nr][nc] = true;
              q.add(new int[]{nr, nc});
            }
          }
        }
      }
    }

    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (lab[i][j] == 0) cnt++;
      }
    }

    max = Math.max(max, cnt);
  }
}
