import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 

        boolean[] isSmall = new boolean[N + 1]; 
        for (int i = 0; i < M; i++) {
            int small = Integer.parseInt(br.readLine());
            isSmall[small] = true;
        }

        if (isSmall[2]) {
            System.out.println(-1);
            return;
        }

        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{2, 1}); 
        visited[2][1] = true;

        int jumpCount = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                int[] now = q.poll();
                int pos = now[0];
                int k = now[1];

                for (int dk = -1; dk <= 1; dk++) {
                    int nextK = k + dk;
                    int nextPos = pos + nextK;

                    if (nextK <= 0 || nextPos > N) continue;
                    if (isSmall[nextPos] || visited[nextPos][nextK]) continue;

                    if (nextPos == N) {
                        System.out.println(jumpCount + 1);
                        return;
                    }

                    q.offer(new int[]{nextPos, nextK});
                    visited[nextPos][nextK] = true;
                }
            }

            jumpCount++;
        }
        System.out.println(-1); 
    }
}
