import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    
    Set<Integer> A = new HashSet<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      A.add(Integer.parseInt(st.nextToken()));
    }

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      if (A.contains(Integer.parseInt(st.nextToken()))) {
        System.out.println(1);
      } else System.out.println(0);
    }
  }
}
