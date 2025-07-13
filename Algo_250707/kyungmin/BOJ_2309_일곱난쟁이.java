package remind;

import java.util.*;

public class BOJ_2309_일곱난쟁이 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] heights = new int[9];
		int sum = 0;

		for (int i = 0; i < 9; i++) {
			heights[i] = sc.nextInt();
			sum += heights[i];
		}

		// 제외할 두 명 찾기
		loop: for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - heights[i] - heights[j] == 100) {
					List<Integer> result = new ArrayList<>();
					for (int k = 0; k < 9; k++) {
						if (k != i && k != j) {
							result.add(heights[k]);
						}
					}
					Collections.sort(result);
					for (int h : result) {
						System.out.println(h);
					}
					break loop;
				}
			}
		}
	}
}
