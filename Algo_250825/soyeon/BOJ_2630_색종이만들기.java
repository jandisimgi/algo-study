mport java.util.*;

public class Main {
  
    static int n;

    static int blue = 0, white = 0;
   
    static int arr[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

   
        for (int y = 0; y < n; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

     
        find(0, 0, n);

       
        System.out.println(white);
        System.out.println(blue);
    }


    private static void find(int x, int y, int num) {
        int sum = 0;

     
        for (int i = y; i < y + num; i++) {
            for (int j = x; j < x + num; j++) {
                sum += arr[i][j];
            }
        }

        if (sum == 0) {
            white++;
        }

        else if (sum == num * num) {
            blue++;
        }
     
        else {
            if (num != 1) { 
                find(x, y, num / 2); 
                find(x + num / 2, y, num / 2);
                find(x, y + num / 2, num / 2); 
                find(x + num / 2, y + num / 2, num / 2); 
            }
        }
    }
}
