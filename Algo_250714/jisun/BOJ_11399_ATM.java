package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11399_ATM {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] P = new int[N];
		for(int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(P);
		
		int[] time = new int[N];
		time[0] = P[0];
		for(int i = 1; i < N; i++) {
			time[i] = time[i-1] + P[i];
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			ans += time[i];
		}
		
		System.out.println(ans);
	}
}
