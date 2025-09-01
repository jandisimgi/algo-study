import java.io.*;
import java.util.*;

public class Main {
   static int[] parent;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken()); 
      int arr[][] = new int[N][M];

      int minH = Integer.MAX_VALUE; 
      int maxH = 0; 

     
      for (int y = 0; y < N; y++) {
         st = new StringTokenizer(br.readLine());
         for (int x = 0; x < M; x++) {
            int n = Integer.parseInt(st.nextToken());
            arr[y][x] = n;

           
            if (n < minH)
               minH = n;

          
            if (n > maxH)
               maxH = n;
         }
      }

      int time[] = new int[257]; 
      for (int h = minH; h <= maxH; h++) {
         int tempB = B;
         
         for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
               if (arr[y][x] < h) { 
                  time[h] += h - arr[y][x]; 
                  tempB -= h - arr[y][x];
               } else if (arr[y][x] > h) { 
                  time[h] += (arr[y][x] - h) * 2;
                  tempB += arr[y][x] - h; 
               }
            }
         }
       
         if (tempB < 0) {
            maxH = h - 1; 
            break; 
         }
      }

      int minTime = Integer.MAX_VALUE;
      int minHigh = minH;
      for (int h = minH; h <= maxH; h++) {
       
         if (time[h] <= minTime) {
            minHigh = h; 
            minTime = time[h];
         }
      }
      
      System.out.println(minTime + " " + minHigh);
   }
}
