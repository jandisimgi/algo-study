package 그리디;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2217_로프 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] rope = new int[N];
		for (int i = 0; i < N; i++) {
			rope[i] = sc.nextInt();
		}

		Arrays.sort(rope);

		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans = Math.max(ans, rope[i] * (N - i));
		}

		System.out.println(ans);
	}
}
