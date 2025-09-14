import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            return;
        }

        boolean visited[] = new boolean[MAX + 1];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { 0, N });
        visited[N] = true;

        while (!q.isEmpty()) {
            int now[] = q.remove();
            int time = now[0];
            int x = now[1];

            int next[] = { x - 1, x + 1, x * 2 };

            for (int d : next) {
                if (d < 0 || d > MAX)
                    continue;
                if (visited[d])
                    continue;
                if (d == K) {
                    System.out.println(time + 1);
                    return;
                }

                visited[d] = true;
                q.offer(new int[] { time + 1, d });
            }
        }
    }
}
