import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS {
	static int N, M, V;
	static List<Integer>[] adj;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int start) {
		visited[start] = true;
		sb.append(start).append(" ");
		
		for(int next : adj[start]) {
			if(!visited[next]) {
				dfs(next);
			}
		}
	}//dfs
	
	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()){
			int curr = queue.poll();
			sb.append(curr).append(' ');
			for(int next : adj[curr]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(next);
				}
			}
		}
	}//bfs
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for(int i = 1; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}
		
		for(int i = 1; i < N+1; i++) {
			Collections.sort(adj[i]);
		}
		
		visited = new boolean[N+1];
		dfs(V);
		sb.append('\n');
		
		Arrays.fill(visited, false);
		bfs(V);
		sb.append('\n');
		
		System.out.println(sb);
	}//main
}//class
