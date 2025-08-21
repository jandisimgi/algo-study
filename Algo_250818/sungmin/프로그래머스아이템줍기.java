import java.util.*;

class Solution {
    static int MAX = 102;
    static int []dx = {1,-1,0,0};
    static int []dy = {0,0,1,-1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 스케일 업 해야함
        for(int []r : rectangle){
            r[0] = 2*r[0];
            r[1] = 2*r[1];
            r[2] = 2*r[2];
            r[3] = 2*r[3];
        }
        int cx = 2*characterX;
        int cy = 2*characterY;
        int ix = 2*itemX;
        int iy = 2*itemY;

        int [][] board = new int[MAX][MAX];


        //도형을 1로 채우기
        for(int []r : rectangle ){
            int x1 = r[0];
            int x2 = r[2];
            int y1 = r[1];
            int y2 = r[3];
            for(int x=x1; x<=x2; x++){
                for(int y=y1; y<=y2; y++){
                    board[x][y]= 1;
                }
            }
        }
        // 도형 내부를 0으로 채우기 
        for(int []r : rectangle ){
            int x1 = r[0];
            int x2 = r[2];
            int y1 = r[1];
            int y2 = r[3];
            for(int x=x1+1; x<x2; x++){
                for(int y=y1+1; y<y2; y++){
                    board[x][y]= 0;
                }
            }
        }

        //bfs로 탐색하기이이이이이
        //방문확인을 위한 2차원배열 -1로 초기화 
        int [][] visited = new int[MAX][MAX];
        for(int r=0; r<MAX; r++){
            for(int c=0; c<MAX; c++){
                visited[r][c]= -1;
            }
        }

        Queue<int[]>q = new ArrayDeque<>();
        q.offer(new int[]{cx,cy});
        visited[cx][cy] = 0;
        while(!q.isEmpty()){
            int []tempRes = q.poll();
            int curx = tempRes[0];
            int cury = tempRes[1];

            //같으면 이제 이동 
            if(curx==ix && cury==iy){
                return visited[curx][cury]/2;
            }

            for(int d=0 ; d<4 ; d++){
                int nx = curx+dx[d];
                int ny = cury+dy[d];

                if(nx<0 || nx >=MAX ||ny<0 || ny >=MAX){
                    continue;
                }
                if(visited[nx][ny]!=-1){
                    continue;
                }
                if(board[nx][ny]!=1){
                    continue;
                }

                visited[nx][ny] = visited[curx][cury] + 1;
                q.offer(new int[]{nx,ny});
            }
        }
        return -1;
    }


}