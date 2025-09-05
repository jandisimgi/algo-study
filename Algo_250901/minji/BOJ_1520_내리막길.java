import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] grid;
    static int[][] memo;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        grid = new int[M][N];
        memo = new int[M][N];
        
        for (int i = 0; i < M; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int result = dfs(0, 0);
        System.out.println(result);
        
        br.close();
    }
    
    static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }
        
        if (memo[x][y] != -1) {
            return memo[x][y];
        }
        
        int paths = 0;
        int currentHeight = grid[x][y];
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                if (grid[nx][ny] < currentHeight) {
                    paths += dfs(nx, ny);
                }
            }
        }
        
        memo[x][y] = paths;
        return paths;
    }
}