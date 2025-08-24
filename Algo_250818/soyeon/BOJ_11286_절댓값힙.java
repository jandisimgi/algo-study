import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        PriorityQueue<Integer> pQueue = new PriorityQueue<>((n1,n2)->{
  
            if(Math.abs(n1) == Math.abs(n2)) {
                return n1-n2;
            }
  
            return Math.abs(n1)-Math.abs(n2);
        });

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num == 0) {
                sb.append(pQueue.isEmpty() ? 0 : pQueue.remove()).append('\n');
                continue;
            }
          
            pQueue.add(num);
        }
        System.out.println(sb);
    }
}
