
    import java.util.*;
    class Solution {
        public int solution(int[] scoville, int K) {
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int s : scoville) pq.add(s);

            int cnt = 0;
            while (pq.size() > 1 && pq.peek() < K) {
                int a = pq.poll();
                int b = pq.poll();
                int mixed = a + b * 2;
                pq.add((int)mixed);
                cnt++;
            }
            return pq.peek() >= K ? cnt : -1;

        }
    }


