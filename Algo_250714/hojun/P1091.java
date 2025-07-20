package BOJ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class P1091 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] P = new int[N];
	    int[] S = new int[N];
	    for (int i = 0; i < N; i++) {
	    	P[i] = sc.nextInt();
	    }
	    for (int i = 0; i < N; i++) {
	    	S[i] = sc.nextInt();
	    }
	    
	    int[] state = new int[N];
	    for (int i = 0; i < N; i++) {
	    	state[i] = i;
	    }
	    int count = 0;
	    
	    Set<String> records = new HashSet<>();
	    while (true) {
	    	boolean ok = true;
	    	for (int i = 0; i < N; i++) {
                if (P[state[i]] != i % 3) {
                    ok = false;
                    break;
                }
            }
	    	
            if (ok) {
                System.out.println(count); 
                return;
            }
            
            String current = Arrays.toString(state);
            if (records.contains(current)) {
                System.out.println(-1);
                return;
            }
            records.add(current);
            
            int[] next = new int[N];
            for (int i = 0; i < N; i++) {
                next[S[i]] = state[i];
            }
            state = next;
            count++;
	    }
	}
}
