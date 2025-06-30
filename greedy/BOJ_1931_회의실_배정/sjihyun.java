import java.io.*;
import java.util.*;

public class BOJ_1931_회의실_배정 {
	private static class Node{
		int start, end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
	
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> {
			if(e1.end != e2.end) return Integer.compare(e1.end, e2.end);
			return Integer.compare(e1.start, e2.start);
		});
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(start, end));
		}
		
		int cnt = 0, time = 0;
		while (!pq.isEmpty()) {
		    Node node = pq.poll();
		    if (node.start >= time) {
		        cnt++;
		        time = node.end;
		    }
		}
		System.out.println(cnt);
	}
}
