package remind;

import java.util.Scanner;

public class BOJ_2563_색종이 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] paper = new boolean[100][100];

		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();

			for (int i1 = x; i1 < x + 10; i1++) {
				for (int j1 = y; j1 < y + 10; j1++) {
					paper[i1][j1] = true;
				}
			}
		}

		int area = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (paper[i][j]) {
					area++;
				}
			}
		}

		System.out.println(area);
	}
}
