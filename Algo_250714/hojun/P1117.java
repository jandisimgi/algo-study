package BOJ;

import java.util.Scanner;

public class P1117 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long W = sc.nextLong();
	    long H = sc.nextLong();
	    long f = sc.nextLong();
	    long c = sc.nextLong();
	    long x1 = sc.nextLong();
	    long y1 = sc.nextLong();
	    long x2 = sc.nextLong();
	    long y2 = sc.nextLong();
		
	    long overlap = Math.min(f, W - f);
        long width = Math.min(x2, overlap) - Math.min(x1, overlap);
        if(width < 0) {
        	width = 0;
        }
        width *= 2;
        width += Math.max(0, x2 - Math.max(x1, overlap));

        long paint = width * (y2 - y1) * (c + 1);
        long result = W * H - paint;
        System.out.println(result);
	}
}
