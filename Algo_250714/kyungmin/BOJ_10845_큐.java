package _daily.july15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class SimpleQueue {
	ArrayDeque<Integer> q;

	public SimpleQueue() {
		this.q = new ArrayDeque<>();
	}

	void push(int x) {
		this.q.add(x);
	}

	int front() {
		Integer res = this.q.peekFirst();
		return res == null ? -1 : res;
	}

	int back() {
		Integer res = this.q.peekLast();
		return res == null ? -1 : res;
	}

	int size() {
		return this.q.size();
	}

	int empty() {
		return this.q.isEmpty() ? 1 : 0;
	}

	int pop() {
		Integer res = this.q.poll();
		return res == null ? -1 : res;
	}
}

public class BOJ_10845_큐 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		SimpleQueue q = new SimpleQueue();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String method = st.nextToken();

			switch (method) {
			case "push":
				int x = Integer.parseInt(st.nextToken());
				q.push(x);
				break;
			case "pop":
				System.out.println(q.pop());
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				System.out.println(q.empty());
				break;
			case "front":
				System.out.println(q.front());
				break;
			case "back":
				System.out.println(q.back());
				break;
			default:
				break;
			}

		}
	}
}
