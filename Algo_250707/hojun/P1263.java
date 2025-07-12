package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1263 {
	
	static class Work implements Comparable<Work> {
		int t;
		int s;
		
		public Work(int t, int s) {
			this.t = t;
			this.s = s;
		}
		
		@Override
		public int compareTo(Work o) {
			return o.s - this.s;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		List<Work> works = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			int T = sc.nextInt();
			int S = sc.nextInt();
			works.add(new Work(T, S));
		}
		
		works.sort(null);
		int start = Integer.MAX_VALUE;
		
		for (Work work : works) {
			start = Math.min(start, work.s);
			start -= work.t;
			if (start < 0) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(start);
		
	}
}
