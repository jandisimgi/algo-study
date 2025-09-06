import java.io.*;
import java.util.*;

public class BOJ_20040_사이클_게임 {

    private static int[] arr;
    private static int N, M;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = i;
    }

    private static void solution() throws IOException {
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (findNode(a) == findNode(b)) {
                System.out.println(i);
                return;
            }
            unionfind(a, b);
        }
        System.out.println(0);
    }

    private static void unionfind(int num1, int num2) {
        int root1 = findNode(num1);
        int root2 = findNode(num2);

        if (root1 < root2) arr[root2] = root1;
        else arr[root1] = root2;
    }

    private static int findNode(int num) {
    	if(arr[num] == num) return num;
    	return arr[num] = findNode(arr[num]);
    }
}
