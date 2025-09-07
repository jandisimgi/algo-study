import java.util.*;
import java.io.*;

class Nation implements Comparable<Nation> {
  int no;
  int gCnt;
  int sCnt;
  int bCnt;

  Nation(int no, int gCnt, int sCnt, int bCnt) {
    this.no = no;
    this.gCnt = gCnt;
    this.sCnt = sCnt;
    this.bCnt = bCnt;
  }

  public int compareTo(Nation o) {
    if (this.gCnt != o.gCnt) return o.gCnt - this.gCnt;
    else if (this.sCnt != o.sCnt) return o.sCnt - this.sCnt;
    else return o.bCnt - this.bCnt;
  }
}

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    PriorityQueue<Nation> pq = new PriorityQueue<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int nation = Integer.parseInt(st.nextToken());
      int gCnt = Integer.parseInt(st.nextToken());
      int sCnt = Integer.parseInt(st.nextToken());
      int bCnt = Integer.parseInt(st.nextToken());

      pq.add(new Nation(nation, gCnt, sCnt, bCnt));
    }

    int rank = 0;
    Nation prev = new Nation(0, 0, 0, 0);
    for (int i = 0; i < N; i++) {
      Nation curr = pq.poll();
      if (prev.gCnt == curr.gCnt && prev.sCnt == curr.sCnt && prev.bCnt == curr.bCnt) {
        rank = rank;
      } else {
        rank = i+1;
      }

      if (curr.no == K) break;
      prev = curr;
    }
    
    System.out.print(rank);
  }
}
