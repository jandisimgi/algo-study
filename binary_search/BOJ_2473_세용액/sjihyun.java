import java.io.*;
import java.util.*;

public class BOJ_2473_세_용액 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		long[] density = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			density[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(density);

		long[] result = new long[3];
		long closestSum = Long.MAX_VALUE;

		outerLoop:
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;

			while (left < right) {
				if (left == i) {
					left++;
					continue;
				}
				if (right == i) {
					right--;
					continue;
				}

				long sum = density[i] + density[left] + density[right];

				if (Math.abs(sum) < closestSum) {
					closestSum = Math.abs(sum);
					result[0] = density[i];
					result[1] = density[left];
					result[2] = density[right];
				}

				if (sum == 0) break outerLoop; 

				if (sum < 0) left++;
				else right--;
			}
		}
		Arrays.sort(result);
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
	}
}
