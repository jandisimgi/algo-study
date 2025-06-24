import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] A = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(A);
    
    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());

    outer:
    for (int i = 0; i < M; i++) {
      int l = 0, r = N-1;
      int num = Integer.parseInt(st.nextToken());
      
      while (l <= r) {
        int m = (l + r) / 2;

        if (num == A[m]) {
          System.out.println(1);
          continue outer;
        } else if (num < A[m]) r = m-1;
        else l = m+1;
      }

      System.out.println(0);
    }
  }
}
