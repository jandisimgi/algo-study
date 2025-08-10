import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int na = 10007;
     
        int[] dp = new int[Math.max(3, n + 1)];
        dp[0] = 1;         
        dp[1] = 1;          
        dp[2] = 3;          

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % na;
        }

        System.out.println(dp[n] % na);
    }
}
