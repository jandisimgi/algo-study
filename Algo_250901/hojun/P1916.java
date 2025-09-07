package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P1916 {

	static class Node implements Comparable<Node> {
        int city, cost;
        Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            String[] arr = br.readLine().split(" ");
            int from = Integer.parseInt(arr[0]);
            int to = Integer.parseInt(arr[1]);
            int cost = Integer.parseInt(arr[2]);
            graph[from].add(new Node(to, cost));
        }
        String[] ab = br.readLine().split(" ");
        int A = Integer.parseInt(ab[0]);
        int B = Integer.parseInt(ab[1]);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[A] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(A, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.city] < now.cost) continue;

            for (Node next : graph[now.city]) {
                if (dist[next.city] > now.cost + next.cost) {
                    dist[next.city] = now.cost + next.cost;
                    pq.add(new Node(next.city, dist[next.city]));
                }
            }
        }
        System.out.println(dist[B]);
    }
}
