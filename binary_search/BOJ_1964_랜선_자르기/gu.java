import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int K = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    long[] lans = new long[K];
    long max = 0;
    
    for (int i = 0; i < K; i++) {
      lans[i] = Long.parseLong(br.readLine());
      max = Math.max(max, lans[i]);
    }

    long l = 0, r = max;
    long m = 0;
    long ans = 0;
    
    while(l <= r) {
      m = (l+r) / 2;

      if (m == 0) {
        ans = 1;
        break;
      }
      
      int cnt = 0;
      
      for (long v : lans) cnt += v / m;

      if (cnt < N) r = m-1;
      else {
        ans = Math.max(ans, m);
        l = m+1;
      }
    }

    System.out.print(ans);
  }
}
