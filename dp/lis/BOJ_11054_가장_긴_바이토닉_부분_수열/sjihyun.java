import java.util.*;
import java.io.*;

public class BOJ_11054_가장_긴_바이토닉_부분_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] numArr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}

		int[] increaseDP = new int[N];
		int[] decreaseDP = new int[N];

		for (int i = 0; i < N; i++) {
			increaseDP[i] = 1;
			for (int j = 0; j < i; j++) {
				if (numArr[i] > numArr[j]) {
					increaseDP[i] = Math.max(increaseDP[i], increaseDP[j] + 1);
				}
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			decreaseDP[i] = 1;
			for (int j = N - 1; j > i; j--) {
				if (numArr[i] > numArr[j]) {
					decreaseDP[i] = Math.max(decreaseDP[i], decreaseDP[j] + 1);
				}
			}
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, increaseDP[i] + decreaseDP[i] - 1);
		}
		System.out.println(result);
	}
}
