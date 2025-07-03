import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] budget = new int[N+1];
    int[] preSum = new int[N+1];

    int max = 0;
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i < N+1; i++) {
      budget[i] = Integer.parseInt(st.nextToken());
      max = Math.max(max, budget[i]);
      preSum[i] = preSum[i-1] + budget[i];
    }

    int M = Integer.parseInt(br.readLine());

    if (preSum[N] <= M) System.out.print(max);
    else {
      int l = 0, r = max;

      int ans = 0;
      while (l <= r) {
        int m  = (l+r) / 2;

        int sum = 0;
        for (int i = 1; i < N+1; i++) {
          if (budget[i] <= m) sum += budget[i];
          else sum += m;
        }

        if (sum <= M) {
          ans = Math.max(ans, m);
          l = m + 1;
        } else {
          r = m - 1;
        }
      }

      System.out.print(ans);
    }
  }
}
