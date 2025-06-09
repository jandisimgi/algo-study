//BOJ_4779_칸토어집합
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_4779_칸토어집합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = br.readLine()) != null) {
			int N = Integer.parseInt(line);
			int length = (int) Math.pow(3, N);
			char[] cs = new char[length];

			for (int i = 0; i < length; i++) {
				cs[i] = '-';
			}

			recur(cs, length / 3, length);

			System.out.println(new String(cs));
		}
	}

	static void recur(char[] cs, int start, int len) {
		System.out.println(new String(cs));
		if (len < 3)
			return;

		int diff = len / 3;

		for (int i = start; i < start + diff; i++) {
			cs[i] = ' ';
		}

		// 0~start+end
		recur(cs, start - diff + diff / 3, diff);
		recur(cs, start + diff + diff / 3, diff);
	}

}
