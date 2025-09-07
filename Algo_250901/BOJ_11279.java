
// 최대 힙

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_11279 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 100,000
		PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> b - a);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(br.readLine());
			pq.offer(input);
			if (input == 0) {
				sb.append(pq.poll());
				sb.append("\n");
			}

		}
		System.out.println(sb);
	}
}
