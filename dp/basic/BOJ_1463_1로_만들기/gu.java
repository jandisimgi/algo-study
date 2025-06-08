import java.util.*;
import java.io.*;

public class Main{
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int dp[] = new int[N+1];
    for (int i = N-1; i > 0; i--) {
      if (i*2 > N && i*3 > N) {
        dp[i] = dp[i+1] + 1;
      } else if (i*3 > N) {
        dp[i] = Math.min(dp[i+1], dp[i*2]) + 1;
      } else {
        dp[i] = Math.min(dp[i+1], Math.min(dp[i*2], dp[i*3])) + 1;
      }
    }
    System.out.print(dp[1]);
  }
}
