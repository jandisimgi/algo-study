package remind;

import java.util.*;

public class BOJ_15685_치킨배달 {
	static int N, M;
	static List<Node> houses = new ArrayList<>();
	static List<Node> chickens = new ArrayList<>();
	static int min = Integer.MAX_VALUE;

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[][] map = new int[N][N];

		// houses & chickens
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1)
					houses.add(new Node(i, j));
				else if (map[i][j] == 2)
					chickens.add(new Node(i, j));
			}
		}

		dfs(0, 0, new ArrayList<>());
		System.out.println(min);
	}

	static void dfs(int start, int depth, List<Node> selected) {
		if (depth == M) {
			min = Math.min(min, cal(selected));
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			selected.add(chickens.get(i));
			dfs(i + 1, depth + 1, selected);
			selected.remove(selected.size() - 1);
		}
	}

	static int cal(List<Node> selectedChickens) {
		int sum = 0;
		for (Node home : houses) {
			int dist = Integer.MAX_VALUE;
			for (Node chicken : selectedChickens) {
				int d = Math.abs(home.r - chicken.r) + Math.abs(home.c - chicken.c);
				dist = Math.min(dist, d);
			}
			sum += dist;
		}
		return sum;
	}
}
