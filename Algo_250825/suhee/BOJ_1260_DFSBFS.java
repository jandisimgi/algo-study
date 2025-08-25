import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,V;
	static List<List<Integer>> graph;
	static boolean [] visited;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 V = Integer.parseInt(st.nextToken());
		 
		 graph = new ArrayList<>();
		 
		 for(int i = 0; i <=N; i++) {
			 graph.add(new ArrayList<>());
		 }
		 
		 
		 visited = new boolean [N+1];
		 
		 for(int i = 0 ; i<M; i++) {
			 st = new StringTokenizer(br.readLine());
			 int A = Integer.parseInt(st.nextToken());
			 int B = Integer.parseInt(st.nextToken());
			 
			 graph.get(A).add(B);
			 graph.get(B).add(A);
		 }
		 
		 for(int i = 1; i<=N; i++) {
			 Collections.sort(graph.get(i));
		 }
		 
		 dfs(V);
		 System.out.println();
		 
		 Arrays.fill(visited, false);
		 bfs(V);
	}
	
	private static void dfs(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		
		for(int next : graph.get(v)) {
			if(!visited[next]) {
				dfs(next);
			}
		}
		
	}
	private static void bfs(int v) {
		
		Queue<Integer> q = new LinkedList<>();
		q.add(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int node = q.poll();
			System.out.print(node + " ");
			
			for(int next : graph.get(node)) {
				if(!visited[next]) {
					q.add(next);
					visited[next] = true;
				}
			}
		}
		
	}

}
