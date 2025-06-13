import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 문제 이해가 ..
// 1, 100, 2 -> 가장 큰 증가하는 수열이 아니고
// 2, 50, 60 -> 가장 큰 증가하는 수열이라고?
// '연속된' 조건이 아님.

// ****** 증가하는 부분 수열의 정의
// 항상 왼쪽에서 오른쪽으로 갈수록 값이 커져야 합니다
public class BOJ_11055_가장큰증가하는부분수열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] a = new int[N];
		int[] dp = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = dp[i] = Integer.parseInt(st.nextToken());
		}

		int max = dp[0]; // 첫 최대값은 반드시 첫번째 원소여야 함.
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i]) {
					// dp[i] + a[j] 가 아님에 주의
					dp[i] = Math.max(dp[i], dp[j] + a[i]);

				}
			}
			max = Math.max(dp[i], max);
		}

		System.out.println(max);
	}
}
