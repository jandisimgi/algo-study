import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) Collections.sort(graph[i]);

        boolean[] visited = new boolean[N + 1];
        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                count++;
                Deque<Integer> stack = new ArrayDeque<>();
                stack.push(i);
                visited[i] = true;

                while (!stack.isEmpty()) {
                    int cur = stack.pop();
                    for (int next : graph[cur]) {
                        if (!visited[next]) {
                            visited[next] = true;
                            stack.push(next);
                        }
                    }
                }
            }
        }

        System.out.println(count);
    }
}
