import java.io.*;
import java.util.*;

public class Main {
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int IX = -1; 
        int IY = -1; 

        char arr[][] = new char[N][M];
        for (int y = 0; y < N; y++) {
            arr[y] = br.readLine().toCharArray(); 
            for (int x = 0; x < M; x++) {
                if (arr[y][x] == 'I') { 
                    IY = y;
                    IX = x;
                }
            }
        }

        boolean visited[][] = new boolean[N][M]; 

        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[] { IX, IY }); 
        visited[IY][IX] = true;

       
        int cnt = 0;

        while (!q.isEmpty()) {
            int now[] = q.remove();
           
            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) 
                    continue;
                if (arr[ny][nx] == 'X') 
                    continue;
                if (visited[ny][nx]) 
                    continue;
                if (arr[ny][nx] == 'P') 
                    cnt++;
                
                visited[ny][nx] = true; 
                q.add(new int[] { nx, ny });
            }
        }
        System.out.println(cnt == 0 ? "TT" : cnt); 
    }
}
