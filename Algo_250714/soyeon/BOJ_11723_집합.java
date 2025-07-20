import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		boolean[] set = new boolean[21];

		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			String cmd = input[0];
			int x = 0;
			if (input.length > 1) {
				x = Integer.parseInt(input[1]);
			}

			switch (cmd) {
				case "add":
					set[x] = true;
					break;
				case "remove":
					set[x] = false;
					break;
				case "check":
					sb.append(set[x] ? "1\n" : "0\n");
					break;
				case "toggle":
					set[x] = !set[x];
					break;
				case "all":
					for (int j = 1; j <= 20; j++) set[j] = true;
					break;
				case "empty":
					for (int j = 1; j <= 20; j++) set[j] = false;
					break;
			}
		}
		System.out.print(sb);
	}
}
