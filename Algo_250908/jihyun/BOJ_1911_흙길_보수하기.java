import java.io.*;
import java.util.*;

public class BOJ_1911_흙길_보수하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        PriorityQueue<long[]> pq = new PriorityQueue<>((x, y) -> {
            if (x[0] == y[0]) return Long.compare(x[1], y[1]);
            return Long.compare(x[0], y[0]);
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long s = Long.parseLong(st.nextToken());
            long e = Long.parseLong(st.nextToken()); 
            pq.add(new long[]{s, e});
        }

        long cur = 0;
        long ans = 0;

        while (!pq.isEmpty()) {
            long[] itv = pq.poll();
            long s = itv[0], e = itv[1];

            long start = Math.max(cur, s);
            if (start >= e) continue;

            long need = e - start;
            long k = (need + L - 1) / L;
            ans += k;
            cur = start + k * L;
        }
        System.out.println(ans);
    }
}
