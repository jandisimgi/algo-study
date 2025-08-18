package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303_전쟁전투 {
	static int N, M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static boolean[][] visited;
	
	static int bfs(int sr, int sc) {
		char color = map[sr][sc];
		Queue<int[]> queue = new ArrayDeque<>();
		visited[sr][sc] = true;
		queue.offer(new int[] {sr, sc});
		
		int cnt = 1;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int r = curr[0];
			int c = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr <0 || nr >= M || nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] != color) continue;
				
				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
				cnt++;
			}
		}
		return cnt;
	
	}//bfs
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[M][N];
		visited = new boolean[M][N];
		
		for(int r = 0; r < M; r++) {
			String input = br.readLine();
			for(int c = 0; c < N; c++) {
				map[r][c] = input.charAt(c);
			}
		}//map
		
        int whitePower = 0;
        int bluePower = 0;

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c]) {
                    int size = bfs(r, c);
                    if (map[r][c] == 'W') whitePower += size * size;
                    else bluePower += size * size; // 'B'
                }
            }
        }

        System.out.println(whitePower + " " + bluePower);
		
	}//main
}//class
