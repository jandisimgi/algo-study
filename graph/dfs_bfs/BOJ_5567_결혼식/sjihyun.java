import java.io.*;
import java.util.*;

public class BOJ_5567_결혼식 {
	
	private static int N;
	private static List<Integer>[] graph;
	
	private static int  findFriend() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N+1];
		
		q.offer(1);
		visit[1] = true;
		
		int cnt = 0, depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s = 0; s<size; s++) {
				
				if(depth == 2) return cnt;
				
				int node = q.poll();
				for(int n : graph[node]) {
					if(!visit[n]) {
						q.offer(n);
						visit[n] = true;
						cnt++;
					}
				}
			}
			depth++;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		System.out.println(findFriend());
	}
}
