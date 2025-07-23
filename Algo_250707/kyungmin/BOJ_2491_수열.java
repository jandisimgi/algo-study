package remind;
import java.util.*;

public class BOJ_2491_수열 {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int N = sc.nextInt();
	        int[] seq = new int[N];
	        for (int i = 0; i < N; i++) {
	            seq[i] = sc.nextInt();
	        }

	        int maxLen = 1;
	        int incLen = 1;
	        int decLen = 1;

	        for (int i = 1; i < N; i++) {
	            if (seq[i] >= seq[i - 1]) {
	                incLen++;
	            } else {
	                incLen = 1;
	            }

	            if (seq[i] <= seq[i - 1]) {
	                decLen++;
	            } else {
	                decLen = 1;
	            }

	            maxLen = Math.max(maxLen, Math.max(incLen, decLen));
	        }

	        System.out.println(maxLen);
	    }
}
