import java.io.*;
import java.util.*;

public class Main {
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int arr[][] = new int[n][m];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[y][x] = 1;
            }
            int cnt = 0;
            Queue<int[]> q = new LinkedList<>();
            boolean visited[][] = new boolean[n][m];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (arr[y][x] == 1 && !visited[y][x]) {
                        cnt++;
                        int a[] = { x, y };
                        q.add(a);
                        while (!q.isEmpty()) {
                            int e[] = q.poll();
                            for (int d = 0; d < 4; d++) {
                                int nx = e[0] + dx[d];
                                int ny = e[1] + dy[d];

                                if (nx < 0 || ny < 0 || nx >= m || ny >= n || arr[ny][nx] == 0 || visited[ny][nx])
                                    continue;
                                q.add(new int[] { nx, ny });
                                visited[ny][nx] = true;
                            }
                        }
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
