/* 시간초과뜸 remove 문제 

import java.io.*;
import java.util.*;

public class BOJ_5719_거의최단경로 {
	
	private static int N;
	private static List<Node>[] graph;
	private static List<Integer>[] path;
	
	private static class Node{
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	private static int[] findPath(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((e1,e2) -> Integer.compare(e1.cost, e2.cost));
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		path = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			path[i] = new ArrayList<>();
		}

		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node out = pq.poll();
			int now = out.to;
			int cost = out.cost;
			
			if (cost > dist[now]) continue;
			
			for (Node next : graph[now]) {
				int newCost = cost + next.cost;
				
				if (dist[next.to] > newCost) {
					dist[next.to] = newCost;
					pq.offer(new Node(next.to, newCost));
					
					path[next.to].clear();
					path[next.to].add(now);
					
				} else if (dist[next.to] == newCost) {
					path[next.to].add(now);
				}
			}
		}
		return dist;
	}
	
	private static void removePath(int to, int from) {
		if(to==from) return;
		
		for(int node : path[to]) {
			graph[node].removeIf(n -> n.to == to); 
			removePath(node, from);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if(N==0 && M==0) break;
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N];
			for(int i=0; i<N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph[from].add(new Node(to, cost));
			}
			
			int[] firstPath = findPath(start);
			
			removePath(end, start);
			
			int[] secondPath = findPath(start);
		
			sb.append(secondPath[end] != Integer.MAX_VALUE ? secondPath[end] : -1).append("\n");
		}
		System.out.println(sb);
	}
}

*/


import java.io.*;
import java.util.*;

public class BOJ_5719_거의최단경로 {

    private static int N;
    private static List<Node>[] graph;
    private static List<Integer>[] path;
    private static boolean[][] skip;

    private static class Node {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int[] findPath(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        path = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            path[i] = new ArrayList<>();
        }

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int now = current.to;
            int cost = current.cost;

            if (cost > dist[now]) continue;

            for (Node next : graph[now]) {
                if (skip[now][next.to]) continue;

                int newCost = cost + next.cost;

                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));

                    path[next.to].clear();
                    path[next.to].add(now);
                    
                } else if (dist[next.to] == newCost) {
                    path[next.to].add(now);
                }
            }
        }

        return dist;
    }

    private static void removePath(int to, int from) {
        if (to == from) return;

        for (int prev : path[to]) {
            if (!skip[prev][to]) {
            	skip[prev][to] = true;
                removePath(prev, from);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }

            skip = new boolean[N][N]; 

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new Node(to, cost));
            }

            int[] dist1 = findPath(start);
            removePath(end, start);
            int[] dist2 = findPath(start);

            sb.append(dist2[end] != Integer.MAX_VALUE ? dist2[end] : -1).append("\n");
        }
        System.out.print(sb);
    }
}