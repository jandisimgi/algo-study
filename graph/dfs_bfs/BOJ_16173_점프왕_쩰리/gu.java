import java.util.*;
import java.io.*;

public class Main{
  static int N;
  static int[][] area;
  static boolean[][] visited;
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = br.readLine().equals("2") ? 2 : 3;
    area = new int[N][N];
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        area[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    visited = new boolean[N][N];
    dfs(0,0);
    System.out.print("Hing");
  }

  static void dfs(int x, int y) {
    if (visited[x][y]) return;
    if (area[x][y] == -1) {
      System.out.print("HaruHaru");
      System.exit(0);
    }

    visited[x][y] = true;

    if (x+area[x][y] < N) {
      dfs(x+area[x][y], y);
    }
    if (y+area[x][y] < N) {
      dfs(x, y+area[x][y]);
    }
  }
}
