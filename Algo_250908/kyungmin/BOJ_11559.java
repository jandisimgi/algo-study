import java.io.*;
import java.util.*;

public class Main {
    static final int R = 12, C = 6;
    static final int[] DR = {1, -1, 0, 0};
    static final int[] DC = {0, 0, 1, -1};

    static char[][] board = new char[R][C];
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < R; i++) board[i] = br.readLine().toCharArray();

        int chain = 0;

        while (true) {
            visited = new boolean[R][C];
            boolean[][] mark = new boolean[R][C];
            boolean exploded = false;

            // 1) 같은 색 4개 이상 그룹을 모두 찾아 'mark'에 표시 (동시 제거 대비)
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (board[r][c] == '.' || visited[r][c]) continue;

                    List<int[]> group = new ArrayList<>(8);
                    char color = board[r][c];
                    dfs(r, c, color, group);

                    if (group.size() >= 4) {
                        exploded = true;
                        for (int[] p : group) mark[p[0]][p[1]] = true;
                    }
                }
            }

            if (!exploded) break;

            // 2) 표시된 칸 동시 제거
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (mark[r][c]) board[r][c] = '.';
                }
            }

            // 3) 중력 적용 (열 단위, 아래부터 채우기)
            applyGravity();

            chain++;
        }

        System.out.println(chain);
    }

    static void dfs(int r, int c, char color, List<int[]> group) {
        visited[r][c] = true;
        group.add(new int[]{r, c});

        for (int d = 0; d < 4; d++) {
            int nr = r + DR[d], nc = c + DC[d];
            if (0 <= nr && nr < R && 0 <= nc && nc < C
                    && !visited[nr][nc] && board[nr][nc] == color) {
                dfs(nr, nc, color, group);
            }
        }
    }

    static void applyGravity() {
        for (int c = 0; c < C; c++) {
            int write = R - 1; // 아래쪽부터 채울 위치
            for (int r = R - 1; r >= 0; r--) {
                if (board[r][c] != '.') {
                    board[write][c] = board[r][c];
                    if (write != r) board[r][c] = '.';
                    write--;
                }
            }
            // 위쪽 남은 칸은 '.'
            for (int r = write; r >= 0; r--) board[r][c] = '.';
        }
    }
}
