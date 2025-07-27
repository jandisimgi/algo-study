class Solution {
    static int[][] dp;
    static int n;
    static int[][] triangle;
    
    private static int dfs(int i, int j){
        if(dp[i][j] != -1) return dp[i][j];
        if(i == n-1) return triangle[i][j];
        return dp[i][j] = triangle[i][j] + Math.max(dfs(i+1, j), dfs(i+1, j+1));
    }
    
    public int solution(int[][] triangle) {
        this.triangle = triangle;
        n = triangle.length;
        dp = new int[n][n];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                dp[i][j] = -1;
            }
        }
        return dfs(0,0);
    }
}
