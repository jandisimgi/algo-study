import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class BOJ_14916_거스름돈 {
    public static void main(String[] args) throws IOException {
        final int INF = Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF); 
        dp[0] = 0; 

        for (int i = 1; i <= N; i++) {
            if (i >= 2 && dp[i - 2] != INF) {
                dp[i] = Math.min(dp[i], dp[i - 2] + 1);
            }
            if (i >= 5 && dp[i - 5] != INF) {
                dp[i] = Math.min(dp[i], dp[i - 5] + 1);
            }
        }

        System.out.println(dp[N] == INF ? -1 : dp[N]);
    }
}
