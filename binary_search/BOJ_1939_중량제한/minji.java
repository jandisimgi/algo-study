import java.io.*;
import java.util.*;

public class Main {
	static class Bridge {
		int to;
		long weight;

		Bridge(int to, long weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Bridge>[] bridges = new List[N + 1];
		boolean[] visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			bridges[i] = new ArrayList<>();
		}

		int n1, n2;
		long weight, minWeight = Long.MAX_VALUE, maxWeight = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			bridges[n1].add(new Bridge(n2, weight));
			bridges[n2].add(new Bridge(n1, weight));
			minWeight = Math.min(minWeight, weight);
			maxWeight = Math.max(maxWeight, weight);
		}

		st = new StringTokenizer(br.readLine());
		n1 = Integer.parseInt(st.nextToken());
		n2 = Integer.parseInt(st.nextToken());
		long midWeight = (minWeight + maxWeight) / 2;

		Queue<Integer> que = new LinkedList<>();

		int curr;
		while (maxWeight >= minWeight) {
			boolean able = false;
			Arrays.fill(visited, false);
			
			que.clear();
			que.add(n1);
			
			while (!que.isEmpty()) {
				curr = que.poll();

				if (curr == n2) {
					able = true;
					break;
				}

				if (visited[curr]) {
					continue;
				}
				visited[curr] = true;

				for (Bridge b : bridges[curr]) {
					if (!visited[b.to] && b.weight >= midWeight) {
						que.add(b.to);
					}
				}
			}

			if (able) {
				minWeight = midWeight + 1;
			} else {
				maxWeight = midWeight - 1;
			}
			midWeight = (minWeight + maxWeight) / 2;
		}
		
		System.out.println(midWeight);
	}// main()
}