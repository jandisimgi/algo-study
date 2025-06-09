package implementation.simulation;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BOJ_13335_트럭 {
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);

	        int n = sc.nextInt(); // 트럭 수
	        int w = sc.nextInt(); // 다리 길이
	        int L = sc.nextInt(); // 최대 하중

	        Queue<Integer> trucks = new LinkedList<>();
	        for (int i = 0; i < n; i++) {
	            trucks.offer(sc.nextInt());
	        }

	        Queue<Integer> bridge = new LinkedList<>();
	        for (int i = 0; i < w; i++) {
	            bridge.offer(0); // 초기 다리 상태는 비어 있음
	        }

	        int time = 0;
	        int totalWeight = 0;

	        while (!bridge.isEmpty()) {
	            time++;

	            // 한 칸 이동 (맨 앞 트럭이 다리에서 빠짐)
	            totalWeight -= bridge.poll();

	            if (!trucks.isEmpty()) {
	                int nextTruck = trucks.peek();

	                if (totalWeight + nextTruck <= L) {
	                    trucks.poll(); // 트럭 진입
	                    bridge.offer(nextTruck);
	                    totalWeight += nextTruck;
	                } else {
	                    bridge.offer(0); // 트럭 진입 못 하고 빈칸 유지
	                }
	            }
	        }

	        System.out.println(time);
	    }
}
