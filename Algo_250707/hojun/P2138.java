package BOJ;

import java.util.Arrays;
import java.util.Scanner;

public class P2138 {
	
	static int N;
	static char[] statusInput, goalInput;
	
	static void flip(char[] arr, int idx) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i >= 0 && i < N) {
                arr[i] = arr[i] == '0' ? '1' : '0';
            }
        }
    }
	
	static int check(boolean first) {
        char[] bulbs = Arrays.copyOf(statusInput, N);
        int count = 0;
        if (first) {
            flip(bulbs, 0);
            count++;
        }
        for (int i = 1; i < N; i++) {
            if (bulbs[i - 1] != goalInput[i - 1]) {
                flip(bulbs, i);
                count++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (bulbs[i] != goalInput[i]) {
            	return Integer.MAX_VALUE;
            }
        }
        
        return count;
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int[] status = new int[N];
		statusInput = sc.next().toCharArray();
		for (int i = 0; i < N; i++) {
			status[i] = statusInput[i] - '0';
		}
		
		int[] goal = new int[N];
	    goalInput = sc.next().toCharArray();
		for (int i = 0; i < N; i++) {
			goal[i] = goalInput[i] - '0';
		}
		
		int answer = Math.min(check(true), check(false));
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
}
