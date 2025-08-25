import java.util.*;
class Solution {
    static List<Integer>[] g;
    public int solution(int n, int[][] wires) {
        g= new ArrayList[n+1];
        //인접리스트 만들기 
        for(int i=1; i<=n ; i++){
            g[i] = new ArrayList<>();
        }
        //인접리스트에 값 넣기 
        for(int[]e : wires){
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        int ans = n;

        for(int[]e : wires){
            int a = e[0];
            int b = e[1];
            boolean []visited = new boolean[n+1];

            int size = dfs(a,visited,a,b);
            ans = Math.min(ans, Math.abs(n-2*size));


        }
        return ans;


    }

    private int dfs (int cur, boolean[]visited, int cutA, int cutB){
        visited[cur] = true;
        int count = 1;

        for (int next : g[cur]){
            if((cur == cutA && next == cutB) || (cur == cutB && next == cutA )){
                continue;
            }
            if(!visited[next]){
                count += dfs(next,visited,cutA,cutB);
            }
        }
        return count;


    }
}