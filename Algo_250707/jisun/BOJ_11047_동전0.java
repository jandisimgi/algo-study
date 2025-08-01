import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ_11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); 
        int K = Integer.parseInt(st.nextToken()); 

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (coins[i] <= K) {
                cnt += K / coins[i];
                K %= coins[i];
            }
        }

        System.out.println(cnt);
    }
}
