import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean []visited = new boolean[
                words.length];
        // 실제 단어를 넣는 QUEUE
        Queue<String> q = new ArrayDeque<>();
        // 해당 단어에 오기까지의 단계를 저장하는 QUEUE
        Queue<Integer>dist = new ArrayDeque<>();

        boolean hasTarget = false;

        for(int i=0; i<words.length; i++){
            if(words[i].equals(target)){
                hasTarget = true;
                break;
            }
        }
        if(!hasTarget){
            return 0;
        }

        q.offer(begin);
        dist.offer(0);

        while(!q.isEmpty()){
            //들어가있는 단어, 단계 빼기
            String str = q.poll();
            int d = dist.poll();
            //만약에 target이랑 같으면 현재까지 들어있는 단계 반환
            if(str.equals(target)){
                return d;
            }
            for(int i=0 ; i<words.length; i++){
                if(!visited[i]&&change(str,words[i])){
                    visited[i]= true;
                    q.offer(words[i]);
                    dist.offer(d+1);
                }
            }
        }
        return 0;



    }

    //str은 넘어온 문자 , word는 전체 문자 중 하나 
    private boolean change(String str, String word){
        //차이가 1일 때만  반환
        int diff =0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)!=word.charAt(i)){
                diff++;
            }
        }
        if(diff == 1){
            return true;
        }
        else{
            return false;
        }

    }
}