import java.io.*;
import java.util.*;

public class Main {
   
   private static int[] candidateOrder, dx = {0,0,1,-1}, dy = {1,-1,0,0};
   private static int[][] map;
   private static List<int[]> virusCandidate;
   private static int N, M, virusCandidateCnt, minTime = Integer.MAX_VALUE;
   
   private static void findVirusSpot(int depth, int last) {
      if (depth == M) {
         int result = calMinTime(candidateOrder);
         if (result != -1) minTime = Math.min(result, minTime);
         return;
      }
      for (int i = last + 1; i < virusCandidateCnt; i++) {
         candidateOrder[depth] = i;
         findVirusSpot(depth + 1, i);
      }
   }
   
   private static int calMinTime(int[] candidateOrder) {
      boolean[][] visit = new boolean[N][N];
      ArrayDeque<int[]> q = new ArrayDeque<>();
      int emptyCnt = 0;
      for (int i = 0; i < N; i++) {
         for (int j = 0; j < N; j++) {
            if (map[i][j] == 0) emptyCnt++;
         }
      }
      if (emptyCnt == 0) return 0;

      for (int i = 0; i < M; i++) {
         int[] idx = virusCandidate.get(candidateOrder[i]);
         int x = idx[0], y = idx[1];
         q.offer(new int[] {x, y, 0}); // x, y, time
         visit[x][y] = true;
      }

      int lastTime = 0;
      while (!q.isEmpty()) {
         int[] out = q.poll();
         int x = out[0], y = out[1], t = out[2];

         for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (visit[nx][ny]) continue;
            if (map[nx][ny] == 1) continue;

            visit[nx][ny] = true;
            if (map[nx][ny] == 0) {
               emptyCnt--;
               lastTime = t + 1;
               if (emptyCnt == 0) return lastTime; 
            }
            q.offer(new int[] {nx, ny, t + 1});
         }
      }
      return -1;
   }
   
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      
      map = new int[N][N];
      virusCandidate = new ArrayList<>();
      for (int i = 0; i < N; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 0; j < N; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
            if (map[i][j] == 2) virusCandidate.add(new int[] {i, j});
         }
      }
      
      virusCandidateCnt = virusCandidate.size();
      candidateOrder = new int[M];
      findVirusSpot(0, -1);
      System.out.println(minTime != Integer.MAX_VALUE ? minTime : -1);
   }
}
