import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Map<Integer, Integer> card = new HashMap<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int idx = Integer.parseInt(st.nextToken());
      card.put(idx, card.getOrDefault(idx, 0) + 1);
    }

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      int idx = Integer.parseInt(st.nextToken());
      sb.append((card.get(idx) != null ? card.get(idx) : 0))
        .append(" ");
    }

    System.out.print(sb);
  }
}
