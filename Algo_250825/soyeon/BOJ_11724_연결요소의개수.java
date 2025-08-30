import java.io.*;
import java.util.*;

public class Main {
   static int parent[];

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = Integer.parseInt(st.nextToken()); 
      int M = Integer.parseInt(st.nextToken()); 

      parent = new int[N + 1];
      for (int i = 1; i <= N; i++) {
         parent[i] = i;
      }

      for (int i = 0; i < M; i++) {
         st = new StringTokenizer(br.readLine());
         int u = Integer.parseInt(st.nextToken());
         int v = Integer.parseInt(st.nextToken());
         union(u, v);
      }

    
      int cnt = 0;
      boolean check[] = new boolean[N + 1]; 
      for (int i = 1; i <= N; i++) {
         int root = find(parent[i]);
         if (!check[root]) {
            check[root] = true;
            cnt++;
         }
      }
      System.out.println(cnt);
      
      
   static void union(int a, int b) {
      int rootA = find(a);
      int rootB = find(b);
      if (rootA != rootB) { 
         parent[rootB] = rootA; 
      }
   }


   static int find(int x) {
      if (x != parent[x])
         parent[x] = find(parent[x]); 
      return parent[x];
   }
}
