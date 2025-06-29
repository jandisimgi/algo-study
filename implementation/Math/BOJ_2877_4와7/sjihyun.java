import java.util.Scanner;

public class BOJ_2877_4와_7 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int k = 1, acc = 0;
		while(true) {
			int cnt = (1<<k);
			if(N <= acc + cnt) break;
			acc += cnt;
			k++;
		}
		int remain = N-1-acc;
		
		StringBuilder sb = new StringBuilder();
		for (int i = k - 1; i >= 0; i--) {
		    if ((remain & (1 << i)) == 0) sb.append('4');
		    else sb.append('7');
		}
		System.out.println(sb);
	}
}
