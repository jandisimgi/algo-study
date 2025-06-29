import java.io.*;
import java.util.*;

public class BOJ_1939_중량제한 {
	
	private static int N, start, end;
	private static List<Node>[] graph;
	
	private static class Node{
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	private static boolean findMaxWeight(int weight) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N+1];
		visit[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int curr = q.poll();
			
			if(curr == end) return true;
			for(Node next : graph[curr]) {
				if(!visit[next.to] && next.cost >= weight) {
					visit[next.to] = true;
					q.offer(next.to);
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) graph[i] = new ArrayList<>();
		
		int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, cost));
			graph[to].add(new Node(from, cost));
			
			left = Math.min(left, cost);
			right = Math.max(right, cost);
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		while(left<=right) {
			int mid = (left+right) /2;
			
			if(findMaxWeight(mid)) left = mid +1 ;
			else right = mid-1;
		}
		System.out.println(right);
	}
}
