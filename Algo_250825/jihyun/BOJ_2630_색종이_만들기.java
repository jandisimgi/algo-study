import java.io.*;
import java.util.*;

public class BOJ_2630_색종이_만들기 {
	
	private static int[][] board;
	private static int white =0, blue=0, N;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void solution(){
		partition(0,0,N);
		System.out.println(white);
		System.out.println(blue);
	}
	
	private static void partition(int x, int y, int size) {
		if(isValid(x,y, size)) {
			if(board[x][y] == 0) white++;
			else blue++;
			return;
		}
		
		int newSize = size /2;
		partition(x+newSize, y+newSize, newSize);
		partition(x+newSize, y, newSize);
		partition(x, y+newSize, newSize);
		partition(x, y, newSize);
	}
	
	private static boolean isValid(int x, int y, int N) {
		int color = board[x][y];
		for(int i=x; i<x+N; i++) {
			for(int j=y; j<y+N; j++) {
				if(board[i][j] != color) return false;
			}
		}
		return true;
	}
}
