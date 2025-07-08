package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class P1107 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		boolean[] buttons = new boolean[10];
		Arrays.fill(buttons, true);
		for (int i = 0; i < M; i++) {
			buttons[sc.nextInt()] = false;
		}
		
		int answer = Math.abs(N - 100);
		for (int i = 0; i < 1000000; i++) {
			int length = check(i, buttons);
			if (length > 0) {
				int count = length + Math.abs(N - i);
				if (count < answer) {
					answer = count;
				}
			}
		}
		
		System.out.println(answer);
	}

	private static int check(int i, boolean[] buttons) {
		if (i == 0) {
			return buttons[0] ? 1 : 0;
		}
		int length = 0;
		while (i > 0) {
			if (!buttons[i % 10]) {
				return 0;
			}
			i /= 10;
			length++;
		}
		return length;
	}
}
