import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] cost = new int[N+1];
    int[] pleasure = new int[N+1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      cost[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= N; i++) {
      pleasure[i] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[N+1][100];
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j < 100; j++) {
        if (cost[i] > j) dp[i][j] = dp[i-1][j];
        else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + pleasure[i]);
      }
    }

    System.out.print(dp[N][99]);
  }
}
