package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1446 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int i = 0; i <= D; i++) {
            edges.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (e > D || c >= (e - s)) continue;
            edges.get(s).add(new int[]{e, c});
        }

        for (int i = 0; i < D; i++) {
            edges.get(i).add(new int[]{i + 1, 1});
        }

        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (dist[curr.position] < curr.distance) continue;
            for (int[] next : edges.get(curr.position)) {
                int npos = next[0];
                int ncost = curr.distance + next[1];
                if (dist[npos] > ncost) {
                    dist[npos] = ncost;
                    pq.add(new Node(npos, ncost));
                }
            }
        }

        System.out.println(dist[D]);
    }

    static class Node implements Comparable<Node> {
        int position;
        int distance;

        public Node(int position, int distance) {
            this.position = position;
            this.distance = distance;
        }

        public int compareTo(Node n) {
            return this.distance - n.distance;
        }
    }
}