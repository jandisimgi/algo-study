package 그리디;

import java.util.Scanner;

public class BOJ_1789_수들의합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long S = sc.nextLong();

		long sum = 0;
		long cnt = 0;
		long i = 1;
		
		while(true) {
			if(sum + i > S) break;
			sum += i;
			cnt++;
			i++;
		}
		
		System.out.println(cnt);
	}
}
