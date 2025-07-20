import java.io.*;
import java.util.*;

public class BOJ_1561_놀이_공원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long left = 0, right = 60000000000L, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;

            long cnt = 0;
            for (int i = 0; i < M; i++) {
                cnt += mid / (long) arr[i];
            }
            cnt += M;

            if (cnt >= N) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long before = 0;
        for (int i = 0; i < M; i++) {
            before += (left - 1) / (long) arr[i];
        }
        before += M;

        for (int i = 0; i < M; i++) {
            if (left % arr[i] == 0) {
                before++;
                if (before == N) {
                    System.out.println(i + 1);
                    break;
                }
            }
        }
    }
}
