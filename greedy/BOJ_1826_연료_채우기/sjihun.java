import java.io.*;
import java.util.*;

public class BOJ_1826_연료_채우기 {
    
    private static class Node {
        int distance, oil;
        public Node(int distance, int oil) {
            this.distance = distance;
            this.oil = oil;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int distance = Integer.parseInt(st.nextToken());
            int oil = Integer.parseInt(st.nextToken());
            list.add(new Node(distance, oil));
        }

        st = new StringTokenizer(br.readLine());
        int destination = Integer.parseInt(st.nextToken());
        int possibleOil = Integer.parseInt(st.nextToken());

        Collections.sort(list,(e1,e2) -> Integer.compare(e1.distance, e2.distance));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int cnt = 0, idx = 0;
        while (possibleOil < destination) {
            while (idx < N && list.get(idx).distance <= possibleOil) {
                pq.offer(list.get(idx).oil);
                idx++;
            }

            if (pq.isEmpty()) {
                System.out.println(-1);
                return;
            }

            possibleOil += pq.poll();
            cnt++;
        }
        System.out.println(cnt);
    }
}
