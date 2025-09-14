import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int max = 0;
        for (int num1 = 1; num1 <= 8; num1++) {
            for (int num2 = num1 + 1; num2 <= 9; num2++) {
                int cnt = 0;
                for (int i = 0; i < N; i++) {
                    if (arr[i] == num1 || arr[i] == num2)
                        cnt++;
                    else {
                        if (cnt > max)
                            max = cnt;
                        cnt = 0;
                    }
                }
                if (cnt > max)
                    max = cnt;
            }
        }
        System.out.println(max);
    }
}
