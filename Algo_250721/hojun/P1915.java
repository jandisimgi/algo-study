package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1915 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		int[][] dp = new int[N][M];
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line[j] - '0';
				dp[i][j] = arr[i][j];
				if (dp[i][j] > max) {
					max = dp[i][j];
				}
			}
		}
		for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (arr[i][j] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    if (dp[i][j] > max) {
                    	max = dp[i][j];
                    }
                }
            }
        }
		System.out.println(max * max);
	}
}
