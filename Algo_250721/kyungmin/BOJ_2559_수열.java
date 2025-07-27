package remind;

import java.util.*;

public class BOJ_2559_수열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 전체 날짜 수
		int K = sc.nextInt(); // 연속 날짜 수
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int windowSum = 0;
		// 초기 구간합
		for (int i = 0; i < K; i++) {
			windowSum += arr[i];
		}

		int maxSum = windowSum;

		// 슬라이딩 윈도우
		// 연속된 범위 탐색
		// 투 포인터 일종
		for (int i = K; i < N; i++) {
			windowSum = windowSum - arr[i - K] + arr[i];
			maxSum = Math.max(maxSum, windowSum);
		}

		System.out.println(maxSum);
	}
}
