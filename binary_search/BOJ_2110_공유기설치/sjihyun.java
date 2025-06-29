package baekjoon.r3_gold;

import java.io.*;
import java.util.*;

public class BOJ_2110_공유기_설치 {
	
	private static int N, C;
	private static int[] coordinate;
	
	private static boolean canInstall(int dist) {
        int cnt = 1, last = coordinate[0];
        for (int i = 1; i < N; i++) {
            if (coordinate[i] - last >= dist) {
            	cnt++;
                last = coordinate[i];
            }
        }
        return cnt >= C;
    }
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		coordinate = new int[N];
		for(int i=0; i<N; i++) {
			coordinate[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(coordinate);
		
		int left = 1, right = coordinate[N - 1] - coordinate[0];  
    int result = 0;
        
		while(left<=right) {
			int mid = (left + right) /2;
			
			if (canInstall(mid)) {
                result = mid;
                left = mid + 1;  
      } else right = mid - 1;
		}
		System.out.println(result);
	}
}
