package BOJ;

import java.util.Scanner;

public class P9466 {
	
	static int n, count;
	static int[] select;
	static boolean[] checked, visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int t = 0; t < T; t++) {
			n = sc.nextInt();
			count = 0;
			
			select = new int[n];
			for (int i = 0; i < n; i++) {
				select[i] = sc.nextInt() - 1;
			}
			
			checked = new boolean[n];
			visited = new boolean[n];
			
			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					dfs(i);
				}
			}
			System.out.println(n - count);
		}
	}

	private static void dfs(int i) {
		visited[i] = true;
		if (!visited[select[i]]) {
			dfs(select[i]);
		} else if (!checked[select[i]]) {
			count++;
			for (int x = select[i]; x != i; x = select[x]) {
				count++;
			}
		}
		checked[i] = true;
	}
}
