import java.io.*;
import java.util.*;

public class BOJ_12738_가장_긴_증가하는_부분_수열_3 {
	
	private static int[] arr;
	private static List<Integer> list;
	
	private static int findIdx(int idx) {
	    int left = 0;
	    int right = list.size() - 1;
	    int result = list.size(); 

	    while (left <= right) {
	        int mid = (left + right) / 2;

	        if(arr[list.get(mid)] < arr[idx]) left = mid + 1;
	        else { //arr[list.get(mid)] >= arr[idx]
	        	result = mid;         
	        	right = mid - 1; 
	        }
	    }
	    return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			if(list.size() == 0) list.add(i);
			else if(arr[list.get(list.size()-1)] < arr[i]) {
				list.add(i);
			}else { //arr[list.get(list.size()-1)] > arr[i]
				int idx = findIdx(i);
				list.set(idx, i); 
			}
		}
		System.out.println(list.size());
	}
}
