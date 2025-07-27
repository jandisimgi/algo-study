import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean[] rest = new boolean[42];

    int cnt = 0;
    for (int i = 0; i < 10; i++) {
      int v = Integer.parseInt(br.readLine()) % 42;
      if (rest[v]) continue;
      rest[v] = true;
      cnt++;
    }
    
    System.out.print(cnt);
  }
}
