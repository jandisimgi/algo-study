package _daily.july15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012_괄호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String token = br.readLine();

			Stack<Character> s = new Stack<>();
			boolean ok = true;
			for (int j = 0; j < token.length(); j++) {
				if (s.isEmpty() && token.charAt(j) == ')') {
					ok = false;
					break;
				}

				switch (token.charAt(j)) {
				case '(':
					s.add('(');
					break;
				case ')':
					s.pop();
					break;
				}
			}
			if (!s.isEmpty()) {
				ok = false;
			}
			System.out.println(ok == true ? "YES" : "NO");

		}
	}
}