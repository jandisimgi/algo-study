package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2178_미로탐색 {
    static int[][] graph;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};

    public static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어난 경우
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                // 이미 방문했거나 벽인 경우
                if (visited[nx][ny] || graph[nx][ny] == 0) continue;

                queue.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                graph[nx][ny] = graph[x][y] + 1; // 최단거리 갱신
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(graph[n - 1][m - 1]);
    }
}
