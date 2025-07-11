import java.io.*;
import java.util.*;

public class BOJ_2457_공주님의_정원 {

	private static PriorityQueue<Day> pq;

	private static class Day {
		int startDate, endDate;

		public Day(int startDate, int endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}

	private static void pickFlower() {
		int cnt = 0;
		int currentDate = 301;
		
		while (currentDate <= 1130) {
			List<Day> list = new ArrayList<>();
			int maxEndDate = 0;
			boolean found = false;

			while (!pq.isEmpty() && pq.peek().startDate <= currentDate) {
				Day flower = pq.poll();
				if (flower.endDate > maxEndDate) {
					maxEndDate = flower.endDate;
					found = true;
				}
				list.add(flower);
			}

			if (!found) {
				System.out.println(0);
				return;
			}

			cnt++;
			currentDate = maxEndDate;
			for (Day nextDate : list) {
				if (nextDate.endDate > maxEndDate) pq.add(nextDate);
			}
		}
		System.out.println(cnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>((e1, e2) -> {
			if (e1.startDate == e2.startDate) return Integer.compare(e2.endDate, e1.endDate);
			return Integer.compare(e1.startDate, e2.startDate);
		});

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMonth = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());

			int startDate = startMonth * 100 + startDay;
			int endDate = endMonth * 100 + endDay;

			if (endDate <= 301 || startDate > 1130) continue;
			pq.add(new Day(startDate, endDate));
		}
		pickFlower();
	}
}
