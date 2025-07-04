import java.io.*;
import java.util.*;

public class BOJ_2212_센서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		PriorityQueue<Integer> gap = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<N-1; i++) {
			gap.add(arr[i+1] - arr[i]);
		}
		
		for(int i=0; i<K-1; i++) {
			gap.poll();
		}
		
		int result = 0;
		while(!gap.isEmpty()) {
			result += gap.poll();
		}
		System.out.println(result);
	}
}
