import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Deque<int[]> dq = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<N; idx++) {
			int num = Integer.parseInt(st.nextToken());
			while(!dq.isEmpty() && dq.peekLast()[1] > num) {
				dq.pollLast();
			}
			dq.offerLast(new int[] {idx, num});
			
			int left = idx-L+1;
			if(dq.peek()[0] < left ) dq.pollFirst();
			
			sb.append(dq.peekFirst()[1] + " ");
		}
		System.out.println(sb);
	}
}
