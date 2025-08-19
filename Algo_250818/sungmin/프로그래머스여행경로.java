import java.util.*;
class Solution {
    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        //한번 사전순으로 정렬하고 시작해야함
        Arrays.sort(tickets, (a,b)->{
            if(!a[0].equals(b[0])){
                return a[0].compareTo(b[0]);
            }
            return a[1].compareTo(b[1]);
        });

        //answer이 결국 공항 배열
        String[] answer = new String[n+1];
        boolean[] used = new boolean[n];
        answer[0] = "ICN";
        dfs("ICN", 0 , tickets, used, answer);
        return answer;

    }
    private boolean dfs(String target, int depth, String[][]tickets, boolean[]used,String[]answer){
        if(depth == tickets.length){
            return true;
        }
        for(int i=0; i<tickets.length; i++){
            if(!used[i] && tickets[i][0].equals(target)){
                used[i]= true;
                answer[depth+1] = tickets[i][1];

                if(dfs(tickets[i][1],depth+1,tickets,used,answer)){
                    return true;
                }

                used[i]=false;
            }
        }

        return false;


    }
}