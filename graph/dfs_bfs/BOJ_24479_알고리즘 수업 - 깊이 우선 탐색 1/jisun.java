package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_24479_알고리즘수업 {
	static int N, M, R;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] order;
	static int cnt = 1;
	
	//DFS 
	public static void dfs(int v) {
		visited[v] = true;
		order[v] = cnt++;
		
		for(int next : graph[v]) {
			if(!visited[next]) {
				dfs(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //정점 수 
		M = Integer.parseInt(st.nextToken()); //간선 수 
		R = Integer.parseInt(st.nextToken()); //시작 정점 
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		order = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//간선 입력 
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		//오름차순 정렬 
		for(int i = 1; i < N+1; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(R);
		
		//결과출력 
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N+1; i++) {
			sb.append(order[i]).append("\n");
		}
		System.out.print(sb);
		
	}//main
}//class

