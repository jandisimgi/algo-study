import java.io.*;

public class BOJ_2448_별찍기_11 {
	private static char[][] map;
    
    private static void draw(int n, int x, int y) {
        if (n == 3) {
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            for (int i = -2; i <= 2; i++) {
                map[x + 2][y + i] = '*';
            }
            return;
        }

        int half = n / 2;
        draw(half, x, y);                 
        draw(half, x + half, y - half);    
        draw(half, x + half, y + half);    
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new char[N][2 * N - 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                map[i][j] = ' ';
            }
        }
        
        draw(N, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (char[] row : map) {
            sb.append(row).append('\n');
        }
        System.out.print(sb);
    }
}
