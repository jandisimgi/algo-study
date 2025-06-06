import java.io.*;
import java.util.*;

public class BOJ_1162_도로포장 {

    private static int N, K;
    private static List<Node>[] graph;

    private static class Node {
        int to, cost;
        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static long findMinCost() {
        long[][] dp = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
        dp[1][0] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[] {1, 0, 0}); 

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int now = (int)cur[0];
            long cost = cur[1];
            int paved = (int)cur[2];

            if (cost > dp[now][paved]) continue;

            for (Node next : graph[now]) {
                long newCost = cost + next.cost;

                if (dp[next.to][paved] > newCost) {
                    dp[next.to][paved] = newCost;
                    pq.offer(new long[] {next.to, newCost, paved});
                }

                if (paved < K && dp[next.to][paved + 1] > cost) {
                    dp[next.to][paved + 1] = cost;
                    pq.offer(new long[] {next.to, cost, paved + 1});
                }
            }
        }

        long result = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            result = Math.min(result, dp[N][i]);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }
        
        System.out.println(findMinCost());
    }
}