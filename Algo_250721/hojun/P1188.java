package BOJ;

import java.util.Scanner;

public class P1188 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int answer = M - gcd(N, M);

        System.out.println(answer);
	}
	
	public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
