import java.io.*;
import java.util.*;

public class BOJ_1508_레이스 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		
		List<Integer> list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		int right = N, left = 1, maxGap = 0;
		while(left<=right) {
			int cnt = 1;
			int select = list.get(0);
			int mid = (right+left)/2;
			
			for(int i=1; i<K; i++) {
				if (list.get(i) - select >= mid) {
			        cnt++;
			        select = list.get(i);
			    }
			}
			
			if(cnt >= M) {
				maxGap = mid;
				left = mid +1;
			}else right = mid-1;
		}
		
		boolean[] pick = new boolean[K];
		int cnt = 1, select = list.get(0);
		pick[0] = true;

		for (int i = 1; i < K && cnt < M; i++) {
		    if (list.get(i) - select >= maxGap) {
		        pick[i] = true;
		        select = list.get(i);
		        cnt++;
		    }
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
		    sb.append(pick[i] ? '1' : '0');
		}
		System.out.println(sb.toString());
	}
}
