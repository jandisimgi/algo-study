package boj;

import java.util.Scanner;

public class P1522 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		
		int countA = 0;
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == 'a') {
				countA++;
			}
		}
		
		if (countA == 0 || countA == input.length()) {
			System.out.println(0);
			
		} else {
			int swap = input.length();
			int countB = 0;
			for (int i = 0; i < countA; i++) {
				if (input.charAt(i) == 'b') {
					countB++;
				}
			}
			swap = countB;
			
			for (int i = 1; i < input.length(); i++) {
				if (input.charAt((i - 1) % input.length()) == 'b') {
					countB--;
				}
				if (input.charAt((i + countA - 1) % input.length()) == 'b') {
					countB++;
				}
				swap = Math.min(swap, countB);
			}
			
			System.out.println(swap);
		}
	}
}
