import java.io.*;

public class BOJ_10422_괄호 {
	private static final int MAX = 5000;
	private static final int MOD = 1_000_000_007;
	private static long[] dp = new long[MAX + 1];
    
    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() {
        dp[0] = 1;
        for (int n = 2; n <= MAX; n += 2) {
            long sum = 0;
            for (int k = 0; k <= n - 2; k += 2) {
                sum = (sum + dp[k] * dp[n - 2 - k]) % MOD;
            }
            dp[n] = sum;
        }
    }

    private static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int i=0; i<T; i++) {
        	int L = Integer.parseInt(br.readLine());
            if (L %2 != 0) sb.append(0).append('\n');    
            else sb.append(dp[L]).append('\n');
        }
        System.out.print(sb);
    }
}
