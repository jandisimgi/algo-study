
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        //방문기록할 배열하나 생성
        boolean[]visited = new boolean[n];
        for(int i=0; i<n; i++){
            //방문하지 않은 애들만
            if(visited[i]==false){
                dfs(i,n,computers,visited);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int i, int n, int[][]computers,boolean[]visited){
        //dfs 들어왔으니
        visited[i] = true;

        for(int j=0; j<n; j++){
            if(computers[i][j]==1 && j!= i && !visited[j])
                dfs(j,n,computers,visited);
        }
    }


}