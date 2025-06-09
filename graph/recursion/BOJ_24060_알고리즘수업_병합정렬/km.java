import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24060_알고리즘수업_병합정렬 {
	static int[] A, tmp;
	static int K;
	static int saveCount = 0;
	static int result = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N];
		tmp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		mergeSort(0, N - 1);

		System.out.println(result);
	}

	static void mergeSort(int p, int r) {
		// 배열이 1개일 때는 처리하지 않는다
		// 길이 1인 배열은 이미 정렬된 상태
		// 더 분할하거나 병합할 필요가 없음
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(p, q);
			mergeSort(q + 1, r);
			merge(p, q, r);
		}
	}

	static void merge(int p, int q, int r) {
		int i = p;
		int j = q + 1;
		int t = 0;

		// 분할된 & 정렬된 배열을 서로 비교
		while (i <= q && j <= r) {
			if (A[i] <= A[j]) {
				tmp[t++] = A[i++];
			} else {
				tmp[t++] = A[j++];
			}
		}

		// 요소를 모두 비교하여 비교되지 않은 배열 하나만 남은 것 처리
		// 좌측 배열이 남은 경우 
		// A[p..q]
		while (i <= q) {
			tmp[t++] = A[i++];
		}
		// 우측 배열이 남은 경우
		// A[q+1..r]
		while (j <= r) {
			tmp[t++] = A[j++];
		}

		// 0부터 채워진 tmp의 값을
		// p부터 시작하는 A의 분할 배열에 맞게 A[p..r]에 복사
		i = p;
		t = 0;
		while (i <= r) {
			A[i] = tmp[t];
			// 저장 횟수를 세고, K번째 저장일 경우 결과 기록 후 종료
			saveCount++;
			if (saveCount == K) {
				result = A[i];
				return;
			}
			i++;
			t++;
		}
	}
}
