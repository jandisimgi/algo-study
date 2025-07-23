package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1490 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		String prefix = Integer.toString(N);
		
		Queue<String> q = new LinkedList<>();
		q.add(prefix);
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				String current = q.poll();
				long num = Long.parseLong(current);
				if (isDivisible(num, N)) {
					System.out.println(num);
					return;
				}
				for (int j = 0; j <= 9; j++) {
					q.add(current + j);
				}
			}
			
		}
	
	}

	private static boolean isDivisible(long num, int N) {
		String str = Integer.toString(N);
		for (int i = 0; i < str.length(); i++) {
	        int digit = str.charAt(i) - '0';
	        if (digit != 0 && num % digit != 0) {
	            return false;
	        }
	    }
	    return true;
	}
}
