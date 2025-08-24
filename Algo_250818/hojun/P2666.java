package boj;

import java.util.Scanner;

public class P2666 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int first = sc.nextInt();
		int second = sc.nextInt();
		
		int K = sc.nextInt();
		int[] order = new int[K];
		for (int i = 0; i < K; i++) {
			order[i] = sc.nextInt();
		}
		
		int[][][] dp = new int[K + 1][N + 1][N + 1];
		for (int i = 0; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				for (int k = 0; k <= N; k++) {
					dp[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		dp[0][first][second] = 0;
		
		for (int i = 0; i < K; i++) {
			int num = order[i];
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					
					if (dp[i][j][k] == Integer.MAX_VALUE) {
						continue;
					}
					
					int current = dp[i][j][k];
					if (num == j || num == k) {
						dp[i + 1][j][k] = Math.min(dp[i + 1][j][k], current);
						
					} else {
						
						int left = current + Math.abs(num - j);
						if (left < dp[i + 1][num][k]) {
							dp[i + 1][num][k] = left;
						}
						
						int right = current + Math.abs(num - k);
						if (right < dp[i + 1][j][num]) {
							dp[i + 1][j][num] = right;
						}
					}
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int j = 1; j <= N; j++) {
			for (int k = 1; k <= N; k++) {
				if (dp[K][j][k] < answer) {
					answer = dp[K][j][k];
				}
			}
		}
		
		System.out.println(answer);
	}
}
