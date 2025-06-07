import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[][] wheel = new int[4][8];
    
    for (int i = 0; i < 4; i++) {
      String line = br.readLine();
      for (int j = 0; j < 8; j++) {
        wheel[i][j] = line.charAt(j) - '0';
      }
    }
    
    int K = Integer.parseInt(br.readLine());
    int[] top = new int[4];
    
    for (int i = 0; i < K; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken())-1;
      int d = Integer.parseInt(st.nextToken());
      
      int topNow = top[n];
      top[n] = (top[n]-d+8) % 8;

      int next = n;
      int topNext = topNow;
      int dirNext = d;
      while (next < 3) {
        if (wheel[next][(topNext+2)%8] == wheel[next+1][(top[next+1]+6)%8]) break;
        topNext = top[next+1];
        top[next+1] = (top[next+1]+dirNext+8) % 8;
        dirNext *= -1;
        next++;
      }

      int prev = n;
      int topPrev = topNow;
      int dirPrev = d;
      while (prev > 0) {
        if (wheel[prev][(topPrev+6)%8] == wheel[prev-1][(top[prev-1]+2)%8]) break;
        topPrev = top[prev-1];
        top[prev-1] = (top[prev-1]+dirPrev+8) % 8;
        dirPrev *= -1;
        prev--;
      }
    }
    
    int point = 0;
    for (int i = 0; i < 4; i++) {
      point += wheel[i][top[i]] * Math.pow(2, i);
    }
    
    System.out.print(point);
  }
}
