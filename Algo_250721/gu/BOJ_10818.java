import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int min = Integer.MAX_VALUE;
    int max = min + 1;
    for (int i = 0; i < N; i++) {
      int v = Integer.parseInt(st.nextToken());
      max = Math.max(max, v);
      min = Math.min(min, v);
    }

    System.out.printf("%d %d", min, max);
  }
}
