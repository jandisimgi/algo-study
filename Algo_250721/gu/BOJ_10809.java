import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] albet = new int[26];
    Arrays.fill(albet, -1);
    
    String word = br.readLine();

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      int num = c-'a';

      if (albet[num] > -1) continue;
      albet[num] = i;
    }

    for (int i = 0; i < albet.length; i++) {
      System.out.print(albet[i] + " ");
    }
  }
}
