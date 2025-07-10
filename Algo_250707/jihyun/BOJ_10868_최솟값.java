import java.io.*;
import java.util.*;

public class BOJ_10868_최솟값 {
	private static int treeHeight, treeSize, leafStart;
	private static int[] tree;
	private static StringBuilder sb = new StringBuilder();
	
	private static void setTree() {
		for(int i=treeSize/2 -1 ; i>0 ; i--) {
			tree[i] = Math.min(tree[i*2], tree[i*2 +1]);
		}
	}
	
	private static void findMinNode(int start, int end) {
		int startIdx = start + leafStart -1;
		int endIdx = end + leafStart -1;
		
		int min = Integer.MAX_VALUE;
		while(startIdx <= endIdx) {
			if(startIdx % 2 == 1) min = Math.min(tree[startIdx++], min);
			if(endIdx % 2 == 0) min = Math.min(tree[endIdx--], min);
			
			startIdx /= 2;
			endIdx /= 2;
		}
		sb.append(min).append("\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		treeHeight = 1;
		int idx = N;
		while((idx /= 2) != 0) {
			treeHeight++;
		}
		
		treeSize = (1<<(treeHeight +1));
		tree = new int[treeSize];
		Arrays.fill(tree, Integer.MAX_VALUE);
		
		leafStart = (1 << treeHeight);
		for(int i=0 ; i<N; i++) {
			tree[leafStart + i] = Integer.parseInt(br.readLine()); 
		}
		setTree();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			findMinNode(start, end);
		}
		System.out.println(sb);
	}
}
