import java.io.*;
import java.util.*;

public class BOJ_5567_결혼식 {
    static class Node {
        int v, d; 
        Node(int v, int d) { this.v = v; this.d = d; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim()); 
        int m = Integer.parseInt(br.readLine().trim()); 

        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        boolean[] visited = new boolean[n + 1];
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(1, 0));
        visited[1] = true;

        int count = 0; 

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.d == 2) continue; 

            for (int nxt : g[cur.v]) {
                if (visited[nxt]) continue;
                visited[nxt] = true;
                q.offer(new Node(nxt, cur.d + 1));
                if (cur.d + 1 >= 1 && cur.d + 1 <= 2) count++;
            }
        }

        System.out.println(count);
    }
}
