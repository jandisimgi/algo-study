import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    if (N == 2) {
      System.out.print(0);
      System.exit(0);
    }

    int[] A = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
    Arrays.sort(A);
    
    int good = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int k = Arrays.binarySearch(A, A[i] - A[j]);
        if (i == j || k < 0) continue;
        if (k == i || k == j) {
          if (k < N-1) {
            if (A[k] == A[k+1] && k+1 != i && k+1 != j) {
              good++;
              break;
            }
          }

          if (k > 0) {
            if (A[k] == A[k-1] && k+1 != i && k+1 != j) {
              good++;
              break;
            } 
          }
        } else {
          good++;
          break;
        }
      }
    }
    System.out.print(good);
  }
}
