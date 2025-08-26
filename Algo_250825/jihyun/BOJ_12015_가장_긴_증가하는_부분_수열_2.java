import java.io.*;
import java.util.*;

public class BOJ_12015_가장_긴_증가하는_부분_수열_2 {
	
	private static int N;
	private static int[] arr;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
	}
	
	private static void solution() {
		List<Integer> LIS = new ArrayList<>();
		LIS.add(arr[0]);
		for(int i=1; i<N; i++) {
			if(LIS.get(LIS.size()-1) < arr[i]) LIS.add(arr[i]);
			else {
				int left = 0, right = LIS.size()-1;
				while(left<right) {
					int mid = (left+right)/2;
					if(LIS.get(mid) >= arr[i]) right = mid;
					else left = mid +1;
				}
				LIS.set(right, arr[i]);
			}
		}
		System.out.println(LIS.size());
	}
}
