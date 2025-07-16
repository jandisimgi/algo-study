import java.util.*;
import java.io.*;

public class BOJ_1516 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] buildings = new int[N+1];
		int[] p = new int[N+1];
		int[] dp = new int[N+1];
		List<Integer>[] fromTo = new ArrayList[N+1];
		List<Integer>[] toFrom = new ArrayList[N+1];
		
		for (int i = 1; i < N+1; i++) {
			fromTo[i] = new ArrayList<>();
			toFrom[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			buildings[i] = Integer.parseInt(st.nextToken());
			dp[i] = buildings[i];
			
			while (true) {
				int token = Integer.parseInt(st.nextToken());
				if (token == -1) break;
				
				fromTo[token].add(i);
				toFrom[i].add(token);
				p[i]++;
			}
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i < N+1; i++) {
			if (p[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			
			for (int i = 0; i < fromTo[curr].size(); i++) {
				int tg = fromTo[curr].get(i); 
				if (--p[tg] == 0) {
					for (int j = 0; j < toFrom[tg].size(); j++) {						
						dp[tg] = Math.max(dp[tg], dp[toFrom[tg].get(j)] + buildings[tg]);
					}
					q.add(tg);
				}
			}
		}
		
		for (int i = 1; i < N+1; i++) System.out.println(dp[i]);
	}
}
