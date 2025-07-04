import java.io.*;

public class BOJ_12904_A와_B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		String input = br.readLine();
		
		StringBuilder sb = new StringBuilder(input);
		while (sb.length() > target.length()) {
		    int lastIdx = sb.length() - 1;
		    if (sb.charAt(lastIdx) == 'A') {
		        sb.deleteCharAt(lastIdx);
		    } else if (sb.charAt(lastIdx) == 'B') {
		        sb.deleteCharAt(lastIdx);
		        sb.reverse();
		    }
		}
		System.out.println(sb.toString().equals(target) ? 1 : 0);
	}
}
