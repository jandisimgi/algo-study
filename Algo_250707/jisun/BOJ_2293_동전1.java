import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ_2293_동전1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 동전 가치 입력
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        // DP 배열 선언 및 초기화
        int[] dp = new int[K + 1];
        dp[0] = 1; // 0원을 만드는 경우의 수는 1

        // 각 동전에 대해 경우의 수 누적
        for (int coin : coins) {
            for (int i = coin; i <= K; i++) {
                dp[i] += dp[i - coin];
            }
        }

        System.out.println(dp[K]);
    }
}
