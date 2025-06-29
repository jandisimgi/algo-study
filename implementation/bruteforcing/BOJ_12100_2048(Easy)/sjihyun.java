import java.io.*;
import java.util.*;

public class BOJ_12100_2048_Easy {

    private static int N;
    private static int[][] board;
    private static int maxNum;

    private static void simulateGame(int[][] curBoard, int depth) {
        if (depth == 5) {
            findMaxBlock(curBoard);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int[][] copied = copyBoard(curBoard);
            moveBlock(copied, d);
            simulateGame(copied, depth + 1);
        }
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copied = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, copied[i], 0, N);
        }
        return copied;
    }

    private static void moveBlock(int[][] board, int dir) {
        if (dir == 0) { // 위
            for (int j = 0; j < N; j++) {
                int[] line = new int[N];
                int idx = 0;
                for (int i = 0; i < N; i++) {
                    if (board[i][j] != 0) line[idx++] = board[i][j];
                }
                merge(line);
                idx = 0;
                for (int i = 0; i < N; i++) board[i][j] = line[idx++];
            }
        } else if (dir == 1) { // 아래
            for (int j = 0; j < N; j++) {
                int[] line = new int[N];
                int idx = 0;
                for (int i = N - 1; i >= 0; i--) {
                    if (board[i][j] != 0) line[idx++] = board[i][j];
                }
                merge(line);
                idx = 0;
                for (int i = N - 1; i >= 0; i--) board[i][j] = line[idx++];
            }
        } else if (dir == 2) { // 오른쪽
            for (int i = 0; i < N; i++) {
                int[] line = new int[N];
                int idx = 0;
                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] != 0) line[idx++] = board[i][j];
                }
                merge(line);
                idx = 0;
                for (int j = N - 1; j >= 0; j--) board[i][j] = line[idx++];
            }
        } else if (dir == 3) { // 왼쪽
            for (int i = 0; i < N; i++) {
                int[] line = new int[N];
                int idx = 0;
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) line[idx++] = board[i][j];
                }
                merge(line);
                idx = 0;
                for (int j = 0; j < N; j++) board[i][j] = line[idx++];
            }
        }
    }

    private static void merge(int[] line) {
        for (int i = 0; i < N - 1; i++) {
            if (line[i] != 0 && line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
                i++; 
            }
        }
        int[] temp = new int[N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            if (line[i] != 0) temp[idx++] = line[i];
        }
        System.arraycopy(temp, 0, line, 0, N);
    }

    private static void findMaxBlock(int[][] curBoard) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxNum = Math.max(maxNum, curBoard[i][j]);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxNum = 0;
        simulateGame(board, 0);
        System.out.println(maxNum);
    }
}
