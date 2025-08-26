
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.text.html.parser.Entity;

public class Main {
	
	static int N,count;
	static int [][] map;
	static boolean [][] visited;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int [N][N];
		visited = new boolean [N][N];
		
		for(int i = 0; i<N; i++) {
			String str = br.readLine().trim();
			for(int j = 0; j<N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 0; i <N; i++) {
			for(int j = 0; j<N; j++) {
				
				if(map[i][j] == 1 && !visited[i][j]) {
				count = 0;
				bfs(i,j);
				result.add(count);
				}
			}
		}
		
		System.out.println(result.size());
		Collections.sort(result);
		for(int num : result) {
			
			System.out.println(num);
		}
		
	}



	private static void bfs(int r, int c) {
		Queue <int[]> q = new LinkedList<>();
		q.add(new int [] {r,c});
		visited[r][c] = true;
		count = 1;
		
		
		while(!q.isEmpty()) {
			int [] pos = q.poll();
			for(int d = 0; d<4; d++) {
				int nr = pos[0] + dr[d];
				int nc = pos[1] + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >=N) continue;
				if(map[nr][nc] == 1 && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] {nr,nc});
					count++;
				}
			}
			
		}
		
	}

}
