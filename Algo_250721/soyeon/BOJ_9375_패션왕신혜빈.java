import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine()); 

        for (int test = 0; test < t; test++) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String line = br.readLine();
                String[] parts = line.split(" ");
                String type = parts[1];

                
                if (!map.containsKey(type)) {
                    map.put(type, 1);
                } else {
                    int value = map.get(type);
                    map.put(type, value + 1);
                }
            }

            int answer = 1;
            for (String key : map.keySet()) {
                int count = map.get(key);
                answer *= (count + 1);
            }

            answer -= 1; 
            System.out.println(answer);
        }
    }
}
