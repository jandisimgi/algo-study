import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    long M = Long.parseLong(st.nextToken());

    int[] trees = new int[N];
    
    int tallest = 0;
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      trees[i] = Integer.parseInt(st.nextToken());
      tallest = Math.max(tallest, trees[i]);
    }

    int low = 0, high = tallest;
    int cutter = 0;
    long answer = 0;
    
    while (low <= high) {
      cutter = (low+high) / 2;
      long sum = 0;

      for (int i = 0; i < N; i++) {
        if (trees[i] <= cutter) continue;
        sum += trees[i] - cutter;
      }

      if (sum > M) {
        answer = cutter;
        low = cutter+1;
      }
      else if (sum < M) high = cutter-1;
      else {
        System.out.print(cutter);
        System.exit(0);
      }
    }

    System.out.print(answer);
  }
}
