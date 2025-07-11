import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Document {
        int index;
        int priority;

        public Document(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int N = Integer.parseInt(input[0]);
            int M = Integer.parseInt(input[1]); 

            String[] priorities = br.readLine().split(" ");
            Queue<Document> queue = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 

            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(priorities[i]);
                queue.offer(new Document(i, priority));
                pq.offer(priority); 
            }

            int printOrder = 0;

            while (!queue.isEmpty()) {
                Document current = queue.poll();

                
                if (current.priority < pq.peek()) {
                    queue.offer(current);
                } else {
                 
                    pq.poll();
                    printOrder++;

                    if (current.index == M) {
                        System.out.println(printOrder);
                        break;
                    }
                }
            }
        }
    }
}
