import java.io.*;
import java.util.*;

public class BOJ_1700_멀티탭_스케줄링 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[K];
		Map<Integer, Integer> cntMap = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int item = Integer.parseInt(st.nextToken());
			cntMap.put(item, cntMap.getOrDefault(item, 0) + 1);
			arr[i] = item;
		}

		int cnt = 0;
		Set<Integer> powerStrip = new HashSet<>();
		for (int i = 0; i < K; i++) {
			int item = arr[i];

			if (powerStrip.contains(item)) {
				cntMap.put(item, cntMap.get(item) - 1);
				continue;
			}

			if (powerStrip.size() == N) {
				int outItem = 0;

				for (int num : powerStrip) {
					if (cntMap.get(num) == 0) {
						outItem = num;
						break;
					}
				}

				if (outItem == 0) {
					Set<Integer> candidate = new HashSet<>(powerStrip);
					for (int j = i + 1; j < K; j++) {
						if (candidate.size() == 1) break;
						candidate.remove(arr[j]);
					}
					for (int num : candidate) {
						outItem = num;
						break;
					}
				}

				powerStrip.remove(outItem);
				cnt++;
			}
			powerStrip.add(item);
			cntMap.put(item, cntMap.get(item) - 1);
		}
		System.out.println(cnt);
	}
}
