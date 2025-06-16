import java.io.*;
import java.util.*;

public class Main {
	
	private static List<Node>[] graph;
	
	private static class Node{
		int from, value;
		public Node(int from, int value) {
			this.from = from;
			this.value = value;
		}
	}
	
	private static int findMaxValue(int N, int M) {
		int[][] dp = new int[N+1][M+1];
		for(int i=0; i<N+1; i++) Arrays.fill(dp[i], -1);
		dp[1][1] = 0;
		
		for(int n=2; n<=N; n++) {
			for(Node node : graph[n]) {
				for (int i = 1; i < M; i++) {
				    if (dp[node.from][i] != -1) {
				        dp[n][i+1] = Math.max(dp[n][i+1], dp[node.from][i] + node.value);
				    }
				}
			}
		}
		
		int result = -1;
		for(int i=1; i<=M; i++) {
			result = Math.max(dp[N][i], result);
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++)  graph[i] = new ArrayList<>();
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			if(from > to) continue;
			graph[to].add(new Node(from, value));
		}
		System.out.println(findMaxValue(N, M));
	}
}
