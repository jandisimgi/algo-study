import java.io.*;
import java.util.*;

class Main {

	private static List<Integer>[] graph;
	private static int N, S, E;
	
	private static int bfs(int cityNum){

		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N+1];

		q.offer(S);
		visit[S]= true;
		visit[cityNum] = true;

		int cnt = 0;
		while(!q.isEmpty()){
			int size = q.size();
			for(int s=0; s<size; s++){
				int node = q.poll();
				if(node == E) return cnt+1;
				
				for(int n : graph[node]){
					if(!visit[n] && n != cityNum){
						visit[n] = true;
						q.offer(n);
					}
				}
			}
			cnt++;
		}
		return -1;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++){
			graph[i] = new ArrayList<>();
		}

		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		for(int i=1; i<N+1; i++){
			if(i == S || i == E) {
				sb.append(-1).append("\n");
				continue;
			}
			int result = bfs(i);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
