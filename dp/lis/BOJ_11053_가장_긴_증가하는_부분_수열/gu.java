import java.util.*;
import java.io.*;

public class Main{
  static int N;
  static int[] A, dp;
  static boolean[] visited;
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    
    A = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

    dp = new int[N];
    visited = new boolean[N];
    dp[0] = 1;
    for (int i = N-1; i > 0; i--) {
      lis(i);
    }

    int ans = 0;
    for (int i = 0; i < N; i++) {
      ans = Math.max(dp[i], ans);
    }

    System.out.print(ans);
  }

  static int lis(int idx) {
    if (idx == 0) return 1;
    if (visited[idx]) return dp[idx];
    
    int maxLis = 0;
    for (int i = idx; i > 0; i--) {
      if (A[idx] > A[i-1]) {
        maxLis = Math.max(maxLis, lis(i-1));
      }
    }

    dp[idx] = maxLis + 1;
    visited[idx] = true;
    return dp[idx];
  }
}
