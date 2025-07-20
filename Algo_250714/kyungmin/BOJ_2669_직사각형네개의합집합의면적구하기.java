package remind;

import java.util.Scanner;

public class BOJ_2669_직사각형네개의합집합의면적구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean[][] grid = new boolean[101][101]; // 1~100

		for (int i = 0; i < 4; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();

			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					grid[x][y] = true;
				}
			}
		}

		int area = 0;
		for (int x = 1; x <= 100; x++) {
			for (int y = 1; y <= 100; y++) {
				if (grid[x][y])
					area++;
			}
		}

		System.out.println(area);
	}
}
