import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int MIL = 1_000_000;
    boolean[] arr = new boolean[2*MIL+1];
    
    for (int i = 0; i < N; i++) {
      arr[Integer.parseInt(br.readLine()) + MIL] = true;
    }
    
    for (int i = 0; i < 2*MIL+1; i++) {
      if (arr[i]) System.out.println(i-MIL);
    }
  }
}
