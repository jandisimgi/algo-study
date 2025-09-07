import java.io.*;
import java.util.*;

public class BOJ_1987_알파벳 {
    static int R, C;
    static char[][] board;
    static int answer = 1;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int usedMask, int length) {
        answer = Math.max(answer, length);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;

            int bit = 1 << (board[nr][nc] - 'A');
            if ((usedMask & bit) == 0) {
                dfs(nr, nc, usedMask | bit, length + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        int startBit = 1 << (board[0][0] - 'A');
        dfs(0, 0, startBit, 1);
        System.out.println(answer);
    }
}
