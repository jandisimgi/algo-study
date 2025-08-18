import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] arr = s.split("-");
		int[] arr1 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String[] temp = arr[i].split("\\+");
			for (int j = 0; j < temp.length; j++) {
				arr1[i] += Integer.parseInt(temp[j]);
			}
		}

		int result = arr1[0];
		for (int i = 1; i < arr1.length; i++) {
			result -= arr1[i];
		}
		
		System.out.println(result);
	}
}
