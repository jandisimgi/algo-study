import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int num = 1;
        int index = 0;

        while (index < n) {
            if (num <= arr[index]) {
                while (num <= arr[index]) {
                    stack.push(num++);
                    sb.append("+\n"); 
                }
            }

            if (stack.peek() == arr[index]) {
                stack.pop();
                sb.append("-\n"); 
                index++;
            } else {
                System.out.println("NO");
                return;
            }
        }

        System.out.print(sb.toString()); 
    }
}
