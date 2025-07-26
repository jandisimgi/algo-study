package BOJ;

import java.util.Scanner;

public class P11058 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[] dp = new long[100];
		
		dp[0] = 1;
		for (int i = 0; i < N; i++) {
			if (i + 1 < N) {
				dp[i + 1] = Math.max(dp[i + 1], dp[i] + 1);
			}
			for (int j = 3; i + j < N; j++) {
				dp[i + j] = Math.max(dp[i + j], dp[i] * (j - 1));
			}
		}
		System.out.println(dp[N - 1]);
	}
}
