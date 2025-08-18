import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    
    int cnt = 0;

    outer:
    for (int i = 0; i < N; i++) {
      String word = br.readLine();
      boolean[] isExist = new boolean[26];
      
      char prev = ' ';
      for (int j = 0; j < word.length(); j++) {
        int idx = word.charAt(j) - 'a';
        if (isExist[idx]) {
          if (prev == word.charAt(j)) continue;
          else continue outer;
        }
        isExist[idx] = true;
        prev = word.charAt(j);
      }

      cnt++;
    }

    System.out.print(cnt);
  }
}
