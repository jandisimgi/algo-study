import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int cnt = 0;
    while (true) {
      try {
        st.nextToken();
        cnt++;
      } catch (Exception e) {
        break;
      }
    }

    System.out.print(cnt);
  }
}
