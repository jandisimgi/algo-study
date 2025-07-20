import java.util.*;

class Solution {
    
    static List<Integer>[] graphIn;
    static List<Integer>[] graphOut;
    static boolean[] visit;
    
    private static int bfs1(int num){
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        visit[num] = true;
        
        while(!q.isEmpty()){
            int node = q.poll();
            
            for(int n : graphIn[node]){
                if(!visit[n]){
                    visit[n] = true;
                    q.offer(n);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
     private static int bfs2(int num){
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(num);
        visit[num] = true;
        
        while(!q.isEmpty()){
            int node = q.poll();
            
            for(int n : graphOut[node]){
                if(!visit[n]){
                    visit[n] = true;
                    q.offer(n);
                    cnt++;
                }
            }
        }
        return cnt;
    }
        
    public int solution(int n, int[][] results) {
        graphIn = new ArrayList[n+1];
        graphOut = new ArrayList[n+1];
        

        for(int i=0; i<=n; i++){
            graphIn[i] = new ArrayList<>();
            graphOut[i] = new ArrayList<>();
        } 
  
        for(int i=0; i<results.length; i++){
            int[] arr = results[i];
            int u = arr[0];
            int v = arr[1];
            graphIn[u].add(v);
            graphOut[v].add(u);
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++){
            visit = new boolean[n+1];
            int cnt1 = bfs1(i);
            int cnt2 = bfs2(i);
            
            if(cnt1 + cnt2 == (n-1)) answer++;
        }
        return answer;
    }
}
