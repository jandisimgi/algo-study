import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int L = Integer.parseInt(br.readLine());
    int M = 1234567891;
    String s = br.readLine();

    long sum = 0;
    for (int i = 0; i < L; i++) {
      sum += (s.charAt(i) - 'a' + 1) * Math.pow(31, i);
    }

    System.out.print(sum % M);
  }
}
