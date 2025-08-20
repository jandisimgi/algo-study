import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String word = br.readLine();
    
    int cnt = 0;

    for (int i = 0; i < word.length(); i++) {
      char tg = word.charAt(i);
      if (tg == '-') continue;
      else if (tg == '=') {
        if (i > 1 && word.charAt(i-1) == 'z' && word.charAt(i-2) == 'd') cnt--;
        continue;
      }
      else if (tg == 'j' && i > 0 && (word.charAt(i-1) == 'l' || word.charAt(i-1) == 'n')) continue;

      cnt++;
    }

    System.out.print(cnt);
  }
}
