import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    int[] house = new int[N];
    for (int i = 0; i < N; i++) house[i] = Integer.parseInt(br.readLine());
    Arrays.sort(house);

    int ans = 0;
    if (N == 2) ans = house[N-1] - house[0];
    else {
      int l = 0, r = house[N-1] - house[0];
      while (l <= r) {
        int dist = (l+r) / 2;
        int cnt = 1;
        for (int i = 0, idx = 0; i < N; i++) {
          if (house[i] - house[idx] >= dist) {
            cnt++;
            idx = i;
          }
        }

        if (cnt >= C) {
          ans = Math.max(ans, dist);
          l = dist + 1;
        } else {
          r = dist - 1; 
        }
      }
    }


    System.out.print(ans);
  }
}
