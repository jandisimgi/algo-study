package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P5972 {
	
	static class Node implements Comparable<Node> {
	    int num;
	    int cost;
	    
	    Node(int num, int cost) {
	        this.num = num;
	        this.cost = cost;
	    }
	    
	    public int compareTo(Node o) {
	        return this.cost - o.cost;
	    }
	}

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt(), M = sc.nextInt();

	    List<List<Node>> graph = new ArrayList<>();
	    for (int i = 0; i <= N; i++)
	        graph.add(new ArrayList<>());

	    for (int i = 0; i < M; i++) {
	        int a = sc.nextInt();
	        int b = sc.nextInt();
	        int c = sc.nextInt();
	        graph.get(a).add(new Node(b, c));
	        graph.get(b).add(new Node(a, c));
	    }

	    int[] dist = new int[N + 1];
	    Arrays.fill(dist, Integer.MAX_VALUE);
	    dist[1] = 0;

	    PriorityQueue<Node> pq = new PriorityQueue<>();
	    pq.add(new Node(1, 0));

	    while (!pq.isEmpty()) {
	        Node cur = pq.poll();
	        if (cur.cost > dist[cur.num]) continue;
	        for (Node next : graph.get(cur.num)) {
	            if (dist[next.num] > dist[cur.num] + next.cost) {
	                dist[next.num] = dist[cur.num] + next.cost;
	                pq.add(new Node(next.num, dist[next.num]));
	            }
	        }
	    }
	    System.out.println(dist[N]);
	}
}
