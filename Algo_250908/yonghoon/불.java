package 알고;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

class Pos {
	int r;
	int c;
	int cnt;
	
	public Pos(int r, int c, int cnt) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
	
}

public class Main2 {

	static int r;
	static int c;
	
	static char [][] arr;
	static List<Pos> list = new ArrayList<>();
	
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	
	static boolean [] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int rr = -1;
		int cc = -1;
		
		
		arr = new char[r][c];
		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'J') {
					rr = i;
					cc = j;
				} else if (arr[i][j] == 'F') {
					list.add(new Pos(i,j,-1));
				}
			}
		}
		
		bfs(rr,cc);
		
	}
	
	public static void bfs(int rr, int cc) {
		boolean [][] ch = new boolean[r][c];
		ch[rr][cc] = true;
		boolean [][] chf = new boolean[r][c];
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(rr,cc,0));
		for (Pos pos : list) {
			q.offer(new Pos(pos.r, pos.c, -1));
			chf[pos.r][pos.c] = true;
		}
		
		while(!q.isEmpty()) {
			int len = q.size();
			for (int i = 0; i < len; i++) {
				Pos p1 = q.poll();
				
				if (arr[p1.r][p1.c] == 'J') {
					for (int p = 0; p < 4; p++) {
						int row = p1.r + dr[p];
						int col = p1.c + dc[p];
						
						//System.out.println(row + " " + col);
						
						if (row == -1 || row == r || col == -1 || col == c) {
							System.out.println(p1.cnt+1);
							return;
						}
						
						if (row < 0 || row >= r || col < 0 || col >= c) continue;
						if (arr[row][col] == '#') continue;
						if (arr[row][col] == 'F') continue;
						if (ch[row][col] == true) continue;
						if (chf[row][col] == true) continue;
						
						// System.out.println(row + " " + col +"와쏘");
						
						ch[row][col] = true;
						arr[row][col] = 'J';
						q.offer(new Pos(row,col,p1.cnt+1));
						
					}
				} else if (arr[p1.r][p1.c] == 'F') {
					for (int p = 0; p < 4; p++) {
						int row = p1.r + dr[p];
						int col = p1.c + dc[p];
						
						// System.out.println(row + " " + col +  "불1");
						
						if (row < 0 || row >= r || col < 0 || col >= c) continue;
						if (arr[row][col] == '#') continue;
						if (chf[row][col] == true) continue;
						
						// System.out.println(row + " " + col +  "불2");
						
						chf[row][col] = true;
						arr[row][col] = 'F';
						q.offer(new Pos(row,col,-1));
						
					}
				}
			}
		}
		
		System.out.println("IMPOSSIBLE");
		
	}
		
}
