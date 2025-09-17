package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P20168 {
	static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, A, B;
    static long C;
    static ArrayList<Edge>[] graph;

    static boolean isPossible(long maxToll) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0])); // [누적비용, 정점]
        pq.add(new long[]{0, A});
        dist[A] = 0;

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long acc = cur[0];
            int now = (int) cur[1];
            if (acc > dist[now]) {
            	continue;
            }

            for (Edge next : graph[now]) {
                if (next.cost > maxToll) continue;
                long nextAcc = acc + next.cost;
                if (nextAcc > C || dist[next.to] <= nextAcc) {
                	continue;
                }
                dist[next.to] = nextAcc;
                pq.add(new long[]{nextAcc, next.to});
            }
        }
        return dist[B] != Long.MAX_VALUE;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextLong();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        long minToll = Long.MAX_VALUE, maxToll = Long.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            long w = sc.nextLong();
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
            minToll = Math.min(minToll, w);
            maxToll = Math.max(maxToll, w);
        }

        long left = minToll, right = maxToll, ans = -1;
        while (left <= right) {
            long mid = (left + right) / 2;
            if (isPossible(mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }
}
