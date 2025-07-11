import java.io.*;
import java.util.*;

public class BOJ_2357_최솟값과_최댓값 {
	
	private static StringBuilder sb = new StringBuilder();
	private static int[] minTree, maxTree;
	private static int leafStart;
	
	private static void setTree() {
		for(int i=leafStart-1; i>0; i--) {
			minTree[i] = Math.min(minTree[i*2], minTree[i*2+1]);
			maxTree[i] = Math.max(maxTree[i*2], maxTree[i*2+1]);
		}
	}
	
	private static void findMinMax(int start, int end) {
		int startIdx = start + leafStart -1;
		int endIdx = end + leafStart -1;
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while(startIdx <= endIdx) {
			if(startIdx %2 == 1) {
				min = Math.min(min, minTree[startIdx]);
				max = Math.max(max, maxTree[startIdx++]);
			}
			
			if(endIdx %2 ==0) {
				min = Math.min(min, minTree[endIdx]);
				max = Math.max(max, maxTree[endIdx--]);
			}
			
			startIdx /= 2;
			endIdx /= 2;
		}
		sb.append(min).append(" ").append(max).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int treeHeight = 1;
		int idx = N;
		while((idx/=2) != 0) treeHeight++;
		
		int treeSize = 1<<(treeHeight+1);
		minTree = new int[treeSize];
		maxTree = new int[treeSize];
		Arrays.fill(minTree, 1000000001);
		Arrays.fill(maxTree, 0);
		
		leafStart = 1<<treeHeight;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			minTree[leafStart+i] = maxTree[leafStart+i] = num;
		}
		
		setTree();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			findMinMax(start, end);
		}
		System.out.println(sb);
	}
}
