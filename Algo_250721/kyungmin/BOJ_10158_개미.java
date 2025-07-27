package remind;

import java.util.*;

public class BOJ_10158_개미 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int w = sc.nextInt(); // 가로
		int h = sc.nextInt(); // 세로
		int p = sc.nextInt(); // 초기 x
		int q = sc.nextInt(); // 초기 y
		int s = sc.nextInt(); // 개미 이동 시간 (s)

		int x = (p + s) % (2 * w);
		if (x > w)
			x = 2 * w - x;

		int y = (q + s) % (2 * h);
		if (y > h)
			y = 2 * h - y;

		System.out.println(x + " " + y);
	}
}
