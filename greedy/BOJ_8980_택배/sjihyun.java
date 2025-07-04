import java.io.*;
import java.util.*;

public class BOJ_8980_택배 {
	private static class Node{
		int from, to, amount;
		public Node(int from, int to, int amount) {
			this.from = from;
			this.to = to;
			this.amount = amount;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>((e1,e2) -> Integer.compare(e1.to, e2.to));
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(from, to, amount));
		}
		
		int maxAmount = 0;
		int[] remain = new int[N+1];
		Arrays.fill(remain, C);
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			int from = node.from;
			int to = node.to;
			int amount = node.amount;
			
			for(int i=from; i<to; i++) {
				amount = Math.min(amount, remain[i]);
			}
			
			maxAmount += amount;
			for(int i=from; i<to; i++) {
				remain[i] -= amount;
			}
		}
		System.out.println(maxAmount);
	}
}
