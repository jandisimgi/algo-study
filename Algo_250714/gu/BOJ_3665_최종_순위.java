import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int t = 0; t < T; t++) {
      StringBuilder sb = new StringBuilder();
      int n = Integer.parseInt(br.readLine());
      int[] lastYear = new int[n+1];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 1; i < n+1; i++) lastYear[i] = Integer.parseInt(st.nextToken());

      int[][] dag = new int[n+1][n+1];
      int[] rank = new int[n+1];
      int[] rankOrigin = new int[n+1];

      for (int i = 1; i < n; i++) {
        for (int j = i+1; j < n+1; j++) {
          dag[lastYear[i]][lastYear[j]]++;
          rank[lastYear[j]]++;
          rankOrigin[lastYear[j]]++;
        }
      }

      int m = Integer.parseInt(br.readLine());

      if (m == 0) {
        for (int i = 1; i < n+1; i++) {
          sb.append(lastYear[i]).append(" ");
        }
        System.out.println(sb);
        continue;
      }

      for (int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (rankOrigin[a] > rankOrigin[b]) {
          rank[a]--; rank[b]++;
          dag[a][b]++; dag[b][a]--;
        } else {
          rank[a]++; rank[b]--;
          dag[a][b]--; dag[b][a]++;
        }
      }

      Queue<Integer> q = new ArrayDeque<>();
      for (int i = 1; i < n+1; i++) {
        if (rank[i] == 0) q.add(i);
      }

      String ans = "IMPOSSIBLE";

      int cnt = 0;
      while (!q.isEmpty()) {
        if (q.size() > 1) {
          ans = "?";
          break;
        }
        
        int curr = q.poll();
        cnt++;
        sb.append(curr).append(" ");

        for (int i = 1; i < n+1; i++) {
          if (dag[curr][i] == 1) {
            if (--rank[i] == 0) {
              q.add(i);
            }
          }
        }
      }

      if (cnt == n) ans = sb.toString();

      System.out.println(ans);
    }
  }
}
