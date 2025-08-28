import java.util.*;
import java.io.*;

public class BOJ_19598_최소_회의실_개수 {
	
	private static PriorityQueue<Node> pq;
	private static List<Node> list;
	private static int N;
	
	private static class Node{
		long start, end;
		public Node(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(pq.size());
	}
	
	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
	
		list = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			long s = Long.parseLong(st.nextToken());
			long e = Long.parseLong(st.nextToken());
			
			list.add(new Node(s,e));
		}
		Collections.sort(list,(e1,e2) -> {
			if (e1.start == e2.start) return Long.compare(e1.end,e2.end);
			return Long.compare(e1.start, e2.start);
		});
	}
	
	private static void solution() {
		pq = new PriorityQueue<>((e1, e2) -> Long.compare(e1.end, e2.end));
		pq.offer(list.get(0));
		for(int i=1; i<N; i++) {
			Node n = list.get(i);
			if(!pq.isEmpty() && n.start >= pq.peek().end) {
				pq.poll();
			}
			pq.offer(n);
		}
	}
}
