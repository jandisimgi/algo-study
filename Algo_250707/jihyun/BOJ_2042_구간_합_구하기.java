import java.io.*;
import java.util.*;

public class BOJ_2042_구간_합_구하기 {

	private static long[] tree;
	private static int treeHeight, treeSize;
	private static StringBuilder sb = new StringBuilder();

	private static void setTree() {
		for (int i = treeSize / 2 - 1; i > 0; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	private static void calSum(int from, int to) {
		long sum = 0;
		int startIdx = from + (int) Math.pow(2, treeHeight) - 1;
		int endIdx = to + (int) Math.pow(2, treeHeight) - 1;
		
		while(startIdx <= endIdx) {
			if(startIdx % 2 == 1) sum += tree[startIdx++];
			if(endIdx % 2 == 0) sum += tree[endIdx--];
			
			startIdx /= 2;
			endIdx /= 2;
		}
		sb.append(sum).append("\n");
	}

	private static void changeNum(int from, long to) { // to만 long
		int idx = from + (int) Math.pow(2, treeHeight) - 1;
		tree[idx] = to;
		
		while(idx > 1) {
			idx /= 2;
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		treeHeight = 1;
		int length = N;
		while ((length /= 2) != 0) {
			treeHeight++;
		}

		treeSize = ((int) Math.pow(2, treeHeight) * 2); 
		tree = new long[treeSize];
		int startIdx = (int) Math.pow(2, treeHeight);
		for (int i = startIdx; i < startIdx + N; i++) {
			tree[i] = Long.parseLong(br.readLine()); 
		}

		setTree();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int caseNum = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			long to = Long.parseLong(st.nextToken()); 

			if (caseNum == 1) changeNum(from, to);
			else if (caseNum == 2) calSum(from, (int) to);
		}
		System.out.println(sb);
	}
}
