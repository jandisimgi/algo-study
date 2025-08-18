package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1005 {
	
	static class Building {
		int time;
		List<Integer> required;
		
		public Building(int time) {
		    this.time = time;
		    this.required = new ArrayList<>();
		}
		
	}
	
	static Building[] buildings;
	static int[] dp;
	
	static int getTime(int target) {
		if (dp[target] != -1) {
			return dp[target];
		}
		
		if (buildings[target].required.isEmpty()) {
			dp[target] = buildings[target].time;
			return dp[target];
		}
		
		int max = 0;
		for (int building : buildings[target].required) {
			int time = getTime(building);
			max = Math.max(max, time);
		}
		dp[target] = max + buildings[target].time;
		return dp[target];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			buildings = new Building[N + 1];
			dp = new int[N + 1];
            Arrays.fill(dp, -1);
			
            st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
                int time = Integer.parseInt(st.nextToken());
                buildings[i] = new Building(time);
            }
			
			for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                buildings[Y].required.add(X);
            }

            int W = Integer.parseInt(br.readLine());
            int result = getTime(W);
            System.out.println(result);
		}
		
	}
}
