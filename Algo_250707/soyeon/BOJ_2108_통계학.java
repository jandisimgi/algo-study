import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] count = new int[8001]; 

        int sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            count[arr[i] + 4000]++;
        }

        Arrays.sort(arr);

        System.out.println(Math.round((double) sum / n));

        System.out.println(arr[n / 2]);

        int maxMode = 0; 
        for (int i = 0; i < 8001; i++) {
            maxMode = Math.max(maxMode, count[i]);
        }

        int mode = 0;
        boolean first = false;
        for (int i = 0; i < 8001; i++) {
            if (count[i] == maxMode) { 
                if (!first) {
                    mode = i - 4000;
                    first = true;
                } else {
                    mode = i - 4000;
                    break;
                }
            }
        }
        System.out.println(mode);

        System.out.println(arr[n - 1] - arr[0]);
    }
}
