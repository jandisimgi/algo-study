package BOJ;

import java.util.Scanner;

public class P1052 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int answer = 0;
		if (N <= K) {
            System.out.println(0);
            return;
        }
        while (Integer.bitCount(N) > K) {
            N++;
            answer++;
        }
		System.out.println(answer);
	}
}
