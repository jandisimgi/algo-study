import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] students = new int[N+1];
		int[] p = new int[N+1];
		List<Integer>[] adj = new ArrayList[N+1];
		
		for (int i = 1; i < N+1; i++) {
			students[i] = i;
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adj[from].add(to);
			p[to]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for (int i = 1; i < N+1; i++) {
			if (p[i] == 0) q.add(i);
		}
		
		while (!q.isEmpty()) {
			int curr = q.poll();
			System.out.print(curr + " ");
			
			for (int i = 0; i < adj[curr].size(); i++) {
				if (--p[adj[curr].get(i)] == 0) {
					q.add(adj[curr].get(i));
				}
			}
		}
	}
}
