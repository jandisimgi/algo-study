import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    Map<String, Integer> map = new HashMap<>();
    
    for (int i = 0; i < N; i++) {
      String word = br.readLine();
      map.put(word, map.getOrDefault(word, 0) + 1);
    }

    Set<Map.Entry<String, Integer>> es = map.entrySet();
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> {
      if (a.getValue() != b.getValue()) return b.getValue() - a.getValue();
      if (a.getKey().length() != b.getKey().length()) return b.getKey().length() - a.getKey().length();
      return a.getKey().compareTo(b.getKey());
    });
    
    for (Map.Entry<String, Integer> entry : es) {
      if (entry.getKey().length() < M) continue;
      pq.add(entry);
    }
    
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    while (!pq.isEmpty()) {
      bw.write(pq.poll().getKey());
      bw.newLine();
    }

    bw.flush();
  }
}
