package BOJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P2212 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> sensors = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			sensors.add(sc.nextInt());
		}
		sensors.sort(null);
		
		List<Integer> distance = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			distance.add(sensors.get(i + 1) - sensors.get(i));
		}
		distance.sort(null);
		
		if (K >= N) {
			System.out.println(0);
			return;
		}
		
		for (int i = 1; i < K; i++) {
			distance.remove(distance.size() - 1);
		}
		
		int sum = 0;
		for (int i = 0; i < distance.size(); i++) {
			sum += distance.get(i);
		}
		System.out.println(sum);
	}
}
