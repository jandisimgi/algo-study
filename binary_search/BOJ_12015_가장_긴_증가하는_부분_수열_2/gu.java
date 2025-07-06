import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    int[] lis = new int[N];
    
    StringTokenizer st = new StringTokenizer(br.readLine());
    lis[0] = Integer.parseInt(st.nextToken());
    int size = 1;
    
    for (int i = 1; i < N; i++) {
      int target = Integer.parseInt(st.nextToken());

      if (lis[size-1] < target) {
        lis[size++] = target;
        continue;
      }
      
      int l = 0, r = size, idx = 0;
      
      while (l <= r && idx < size) {
        idx = (l+r) / 2;
        if (lis[idx] > target) r = idx-1;
        else if (lis[idx] < target) l = idx+1;
        else break;
      }

      if (lis[idx] < target) lis[idx+1] = target;
      else lis[idx] = target;
    }

    System.out.print(size);
  }
}
