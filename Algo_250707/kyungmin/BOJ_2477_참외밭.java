package remind;

import java.util.*;

public class BOJ_2477_참외밭 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 면적당 참외 개수
		int K = sc.nextInt();

		int[] dir = new int[6];
		int[] len = new int[6];

		int maxWidth = 0, maxHeight = 0;
		int maxWidthIdx = -1;
		int maxHeightIdx = -1;

		for (int i = 0; i < 6; i++) {
			dir[i] = sc.nextInt();
			len[i] = sc.nextInt();

			if (dir[i] == 1 || dir[i] == 2) { // 가로
				if (len[i] > maxWidth) {
					maxWidth = len[i];
					maxWidthIdx = i;
				}
			} else { // 세로
				if (len[i] > maxHeight) {
					maxHeight = len[i];
					maxHeightIdx = i;
				}
			}
		}

		int minWidth = len[(maxWidthIdx + 3) % 6];
		int minHeight = len[(maxHeightIdx + 3) % 6];

		int area = (maxWidth * maxHeight) - (minWidth * minHeight);
		int result = area * K;

		System.out.println(result);
	}
}
