package deque;

import java.io.*;
import java.util.*;

public class BOJ_10866_덱 {

	static class IntDeque {
		private final int[] a;
		private final int cap;
		private int head = 0;
		private int tail = 0;
		private int size = 0;

		IntDeque(int capacity) {
			this.cap = capacity;
			this.a = new int[cap];
		}

		void pushFront(int x) {
			head = (head - 1 + cap) % cap;
			a[head] = x;
			size++;
		}

		void pushBack(int x) {
			a[tail] = x;
			tail = (tail + 1) % cap;
			size++;
		}

		int popFront() {
			if (size == 0)
				return -1;
			int v = a[head];
			head = (head + 1) % cap;
			size--;
			return v;
		}

		int popBack() {
			if (size == 0)
				return -1;
			tail = (tail - 1 + cap) % cap;
			int v = a[tail];
			size--;
			return v;
		}

		int front() {
			return size == 0 ? -1 : a[head];
		}

		int back() {
			return size == 0 ? -1 : a[(tail - 1 + cap) % cap];
		}

		int size() {
			return size;
		}

		int empty() {
			return size == 0 ? 1 : 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		IntDeque dq = new IntDeque(N + 5);

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			if (line.startsWith("push_front")) {
				int x = Integer.parseInt(line.substring(11).trim());
				dq.pushFront(x);
			} else if (line.startsWith("push_back")) {
				int x = Integer.parseInt(line.substring(10).trim());
				dq.pushBack(x);
			} else {
				switch (line) {
				case "pop_front":
					sb.append(dq.popFront()).append('\n');
					break;
				case "pop_back":
					sb.append(dq.popBack()).append('\n');
					break;
				case "size":
					sb.append(dq.size()).append('\n');
					break;
				case "empty":
					sb.append(dq.empty()).append('\n');
					break;
				case "front":
					sb.append(dq.front()).append('\n');
					break;
				case "back":
					sb.append(dq.back()).append('\n');
					break;
				}
			}
		}
		System.out.print(sb);
	}
}