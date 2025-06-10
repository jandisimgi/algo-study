package dp;

import java.util.Scanner;

public class BOJ_1463_1로만들기 {
	static int min = Integer.MAX_VALUE;
	static int[] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int X = sc.nextInt();
		memo = new int[X + 1];

		for (int i = 2; i <= X; i++) {
			memo[i] = memo[i - 1] + 1;

			if (i % 3 == 0) {
				memo[i] = Math.min(memo[i], memo[i / 3] + 1);
			}
			if (i % 2 == 0) {
				memo[i] = Math.min(memo[i], memo[i / 2] + 1);
			}
		}

		System.out.println(memo[X]);
		sc.close();
	}

	static void dp(int x) {

		for (int i = 2; i <= x; i++) {
			if (i % 3 == 0) {
				memo[i] = memo[i / 3] + 1;
			}
			if (i % 2 == 0) {
				memo[i] = memo[i / 2] + 1;
			}
			int temp = memo[i - 1] + 1;
			if (memo[i] > temp || memo[i] == 0) {
				memo[i] = temp;
			}
		}
	}
}
