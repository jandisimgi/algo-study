import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2579_계단오르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[N + 1];
		for (int i = 1; i < stairs.length; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[N + 1];

		dp[1] = stairs[1];
		if (N == 1) {
			System.out.println(stairs[1]);
			return;
		}

		dp[2] = stairs[1] + stairs[2];
		if (N >= 3) {
			// 1번째 + 3번째 vs. 2번째 + 3번째
			dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);

			for (int i = 4; i < N + 1; i++) {
				// dp[i-3]이나 dp[i-2]는 결과값만 저장한것이기 때문에
				// dp자리에 저장된 값이 규칙에 맞게 온것이라고 할 수 있음 = 최적해
				dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
			}
		}
		System.out.println(dp[N]);
	}
}
