package remind;

import java.util.*;

public class BOJ_2628_종이자르기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int width = sc.nextInt(); // 종이 가로 길이
		int height = sc.nextInt(); // 종이 세로 길이
		int N = sc.nextInt(); // 점선 개수

		List<Integer> horizontal = new ArrayList<>();
		List<Integer> vertical = new ArrayList<>();

		horizontal.add(0);
		horizontal.add(height);
		vertical.add(0);
		vertical.add(width);

		for (int i = 0; i < N; i++) {
			int dir = sc.nextInt(); // 0: 가로 자름, 1: 세로 자름
			int pos = sc.nextInt();

			if (dir == 0) {
				horizontal.add(pos);
			} else {
				vertical.add(pos);
			}
		}

		Collections.sort(horizontal);
		Collections.sort(vertical);

		int maxH = 0;
		for (int i = 1; i < horizontal.size(); i++) {
			maxH = Math.max(maxH, horizontal.get(i) - horizontal.get(i - 1));
		}

		int maxW = 0;
		for (int i = 1; i < vertical.size(); i++) {
			maxW = Math.max(maxW, vertical.get(i) - vertical.get(i - 1));
		}

		System.out.println(maxH * maxW);
	}
}
