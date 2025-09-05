import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
        }
        
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int l: list) {
            map.put(l, index++);
        }
        for(int i=0; i<N; i++) {
            sb.append(map.get(arr[i])).append(' ');
        }

        System.out.println(sb);
    }
}
