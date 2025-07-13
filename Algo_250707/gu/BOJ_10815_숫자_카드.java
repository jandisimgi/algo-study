import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] cards = new int[N];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) cards[i] = Integer.parseInt(st.nextToken());

    Arrays.sort(cards);

    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) System.out.print((Arrays.binarySearch(cards, Integer.parseInt(st.nextToken())) >= 0 ? 1 : 0) + " ");
  }
}
