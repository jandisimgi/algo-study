import java.util.*;
import java.io.*;

class House {
  int r, c, dist;
  
  House(int r, int c, int dist) {
    this.r = r;
    this.c = c;
    this.dist = dist;
  }
}

public class Main{
  static int N, M, house, min;
  static int[][] town;
  static boolean[][] visited;
  
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    town = new int[N][N];
    
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        town[i][j] = Integer.parseInt(st.nextToken());
        if (town[i][j] == 1) house++;
      }
    }

    min = Integer.MAX_VALUE;
    List<House> street = new ArrayList<>();
    pickChicken(0, -1, 0, street);

    System.out.print(min);
  }

  static void pickChicken(int r, int c, int depth, List<House> street) {
    for (int i = r; i < N; i++) {
      if (i != r) c = -1;
      for (int j = c+1; j < N; j++) {
        if (town[i][j] == 2) {
          List<House> addedStreet = addToList(i, j, street);
          
          if (depth+1 == M) {
            PriorityQueue<House> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
            
            for (int k = 0; k < addedStreet.size(); k++) pq.add(addedStreet.get(k));
            
            int dist = 0;
            int pollCnt = 0;
            visited = new boolean[N][N];
            
            while (pollCnt < house && !pq.isEmpty()) {
              House h = pq.poll();

              if (visited[h.r][h.c]) continue;
              
              visited[h.r][h.c] = true;
              pollCnt++;
              dist += h.dist;
            }
            
            if (dist != 0) min = Math.min(min, dist);
            continue;
          }
          
          pickChicken(i, j, depth+1, addedStreet);
        }
      }
    }
  }

  static List<House> addToList(int r, int c, List<House> street) {
    List<House> streetTmp = new ArrayList<>(street);
    
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (town[i][j] == 1) streetTmp.add(new House(i, j, Math.abs(r-i) + Math.abs(c-j)));
      }
    }

    return streetTmp;
  }
}
