import java.io.*;
import java.util.*;


public class BOJ_10282_해킹 {
	
	private static int N;
	private static List<Node>[] graph;
	private static StringBuilder sb = new StringBuilder();
	
	private static class Node{
		int to, time;
		public  Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
	
	private static void findTime(int start) {
		int[] minTime = new int[N+1];
		Arrays.fill(minTime, Integer.MAX_VALUE);
		minTime[start] = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1,e2) -> Integer.compare(e1[1], e2[1]));
	    pq.offer(new int[]{start, 0});
	    
	    while (!pq.isEmpty()) {
	        int[] out = pq.poll();
	        int n = out[0], t = out[1];
	        
	        if(minTime[n] < t) continue;
	        
	        for(Node node : graph[n]) {
	        	int newTime = t + node.time;
	        	if(minTime[node.to] > newTime) {
	        		minTime[node.to] = newTime;
	        		pq.offer(new int[] {node.to, newTime});
	        	}
	        }
	    }
	    
	    int cnt = 0, result = 0;
	    for(int i=1; i<N+1; i++) {
	    	if(minTime[i] != Integer.MAX_VALUE) {
	    		cnt++;
	    		result = Math.max(result, minTime[i]);
	    	}
	    }
	    sb.append(cnt+" "+result).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			for(int i=0; i<N+1; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0; i<D; i++){
				st = new StringTokenizer(br.readLine());
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph[from].add(new Node(to, cost));
			}
			findTime(start);
		}
		System.out.println(sb);
	}
}
