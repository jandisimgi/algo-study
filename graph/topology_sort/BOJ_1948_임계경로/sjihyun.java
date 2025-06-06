import java.io.*;
import java.util.*;

public class BOJ_1948_임계경로 {
	
	private static int N;
	private static int[] inDegree;
	private static List<Node>[] graph;
	private static List<Node>[] reverseGraph;
	private static int[] time;
	
	private static class Node{
		int to, time;
		public Node(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
	
	private static void findTime(int start, int end) {
	    Queue<Integer> q = new ArrayDeque<>();
	    time = new int[N + 1];

	    q.offer(start);
	    time[start] = 0;

	    while (!q.isEmpty()) {
	        int node = q.poll();

	        for (Node next : graph[node]) {
	            int newTime = time[node] + next.time;
	            time[next.to] = Math.max(time[next.to], newTime);
	            inDegree[next.to]--;
	            if (inDegree[next.to] == 0) {
	                q.offer(next.to);
	            }
	        }
	    }
	    cntRoad(time[end], end);
	}
	
	private static void cntRoad(int maxTime, int end) {
	    boolean[] visit = new boolean[N + 1];
	    Queue<Integer> q = new ArrayDeque<>();
	    q.offer(end);
	    visit[end] = true;

	    int cnt = 0;

	    while (!q.isEmpty()) {
	        int curr = q.poll();

	        for (Node prev : reverseGraph[curr]) {
	            if (time[curr] == time[prev.to] + prev.time) {
	            	cnt++;
	                if (!visit[prev.to]) {
	                    visit[prev.to] = true;
	                    q.offer(prev.to);
	                }
	            }
	        }
	    }
	    System.out.println(maxTime);
	    System.out.println(cnt);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		inDegree = new int[N+1];
		graph = new ArrayList[N+1];
		reverseGraph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
			reverseGraph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			graph[from].add(new Node(to, time));
			reverseGraph[to].add(new Node(from, time));
			inDegree[to]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		findTime(start, end);
	}
}
