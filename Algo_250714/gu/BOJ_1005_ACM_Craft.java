import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for (int t = 0; t < T; t++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      
      int[] buildings = new int[N+1];
      int[] linked = new int[N+1];
      int[] dp = new int[N+1];

      st = new StringTokenizer(br.readLine());
      for (int i = 1; i <= N; i++) {
        buildings[i] = Integer.parseInt(st.nextToken());
        dp[i] = buildings[i];
      }
      
      List<Integer>[] adj = new ArrayList[N+1];
      for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
      
      for (int i = 0; i < K; i++) {
        st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        
        adj[from].add(to);
        
        linked[to]++;
      }
      
      int W = Integer.parseInt(br.readLine());

      Queue<Integer> q = new ArrayDeque<>();

      for (int i = 1; i <= N; i++) {
        if (linked[i] == 0) q.add(i);
      }
      
      while (!q.isEmpty()) {
        int curr = q.poll();
        
        for (int tg : adj[curr]) {
          dp[tg] = Math.max(dp[tg], dp[curr] + buildings[tg]);
          if (--linked[tg] == 0) q.add(tg);
        }
      }

      System.out.println(dp[W]);
    }
  }
}
