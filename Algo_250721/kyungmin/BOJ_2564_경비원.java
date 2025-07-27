package remind;

import java.util.*;

public class BOJ_2564_경비원 {

	static int w, h;

	// 각 위치를 1차원 선상으로 환산
	static int toLinePoint(int dir, int dist) {
		if (dir == 1)
			return dist;
		else if (dir == 2)
			return 2 * w + h - dist;
		else if (dir == 3)
			return 2 * w + 2 * h - dist;
		else
			return w + dist; // dir == 4
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		w = sc.nextInt();
		h = sc.nextInt();
		int n = sc.nextInt();

		// 상점
		int[] stores = new int[n];
		for (int i = 0; i < n; i++) {
			int dir = sc.nextInt();
			int dist = sc.nextInt();
			stores[i] = toLinePoint(dir, dist);
		}

		int dir = sc.nextInt();
		int dist = sc.nextInt();

		int dg = toLinePoint(dir, dist); // 동근

		int perimeter = 2 * (w + h); // 둘레
		int sum = 0;

		for (int store : stores) {
			int diff = Math.abs(dg - store);
			sum += Math.min(diff, perimeter - diff);
		}

		System.out.println(sum);
	}
}
