package 그래프;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class BOJ_1325_효율적인해킹 {
	static List<Integer>[] computer;
	static int[] hacking;
	
	static int bfs(int v) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[computer.length];
		int cnt = 0;
		
		queue.offer(v);
		visited[v] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int next : computer[curr]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.offer(next);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		computer = new ArrayList[N+1];
		hacking = new int[N+1];
		
		for(int i = 1; i < N+1; i++) {
			computer[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			computer[B].add(A);
		}
		
		int max = 0;
		for(int i = 1; i < N+1; i++) {
			hacking[i] = bfs(i);
			max = Math.max(max, hacking[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i < N+1; i++) {
			if(hacking[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.print(sb);
		
		
	}//main
}//class
