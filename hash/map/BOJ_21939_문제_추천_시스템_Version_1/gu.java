import java.util.*;
import java.io.*;

public class Main{
  static class Prob  implements Comparable<Prob> {
    int key, value;

    Prob(int key, int value) {
      this.key = key;
      this.value = value;
    }

    public int compareTo(Prob p) {
      if (this.value != p.value) return this.value - p.value;
      return this.key - p.key;
    }
  }
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    Map<Integer, Integer> probs = new HashMap<>();
    PriorityQueue<Prob> minPq = new PriorityQueue<>((a, b) -> a.compareTo(b));
    PriorityQueue<Prob> maxPq = new PriorityQueue<>((a, b) -> b.compareTo(a));

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int key = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      Prob p = new Prob(key, value);
        
      probs.put(key, value);
      minPq.add(p);
      maxPq.add(p);
    }

    int M = Integer.parseInt(br.readLine());

    for (int i = 0; i < M; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String cmd = st.nextToken();
      if (cmd.equals("recommend")) {
        String x = st.nextToken();
        PriorityQueue<Prob> pq = null;
        if (x.equals("1")) pq = maxPq;
        else pq = minPq;

        while (true) {
          Prob p = pq.peek();
          
          if (!probs.containsKey(p.key) || probs.get(p.key) != p.value) {
            pq.poll();
            continue;
          }
          
          sb.append(p.key).append("\n");
          break;
        }
      } else if (cmd.equals("add")) {
        int key = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        Prob p = new Prob(key, value);
        
        probs.put(key, value);
        minPq.add(p);
        maxPq.add(p);
      } else {
        probs.remove(Integer.parseInt(st.nextToken()));
      }
    }

    bw.write(sb.toString());
    bw.flush();
  }
}
