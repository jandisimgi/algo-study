package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1026_보물 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		int[] B = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(B);
		for(int i = 0; i < B.length / 2; i++) {
			int tmp = B[i];
			B[i] = B[B.length - 1 -i];
			B[B.length - 1 -i] = tmp;
		}
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			sum += A[i] * B[i];
		}
		
		System.out.println(sum);
	}
}
