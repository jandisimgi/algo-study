import java.io.*;
import java.util.*;

public class BOJ_8986_전봇대 {
    static int N;
    static int[] idx;

    private static long getCost(long d) {
        long cost = 0;
        for (int i = 0; i < N; i++) {
            long target = 1L * i * d;
            cost += Math.abs(idx[i] - target);
        }
        return cost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        idx = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            idx[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        long left = 1;
        long right = 1_000_000_000;

        while (right - left >= 3) {
            long mid1 = (2 * left + right) / 3;
            long mid2 = (left + 2 * right) / 3;

            long cost1 = getCost(mid1);
            long cost2 = getCost(mid2);

            if (cost1 < cost2) {
                right = mid2;
            } else {
                left = mid1;
            }
        }

        long answer = Long.MAX_VALUE;
        for (long d = left; d <= right; d++) {
            answer = Math.min(answer, getCost(d));
        }
        System.out.println(answer);
    }
}
