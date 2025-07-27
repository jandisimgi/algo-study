import java.util.*;

class Solution {
    
    private static String target;
    private static String[] words;
    private static Set<String> visit;
    private static int MinCnt = Integer.MAX_VALUE;
    
    private static boolean isDiffOkay(String check1, String check2) {
        int cnt = 0;
        for (int i = 0; i < check1.length(); i++) {
            if (check1.charAt(i) != check2.charAt(i)) cnt++;
        }
        return cnt == 1; 
    } 
    
    private static void bfs(String str){
        int cnt = 0;
        Queue<String> q = new ArrayDeque<>();
        q.offer(str);
        visit.add(str);
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                String wordStr = q.poll();
            
                if (wordStr.equals(target)) {
                    MinCnt = Math.min(MinCnt, cnt);
                    return;
                }

                for(String nextStr : words){
                    if(!visit.contains(nextStr) && isDiffOkay(wordStr,nextStr)) {
                        q.offer(nextStr);
                        visit.add(nextStr);
                    }
                }
            }
            cnt++; 
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        this.target = target;
        this.words = words;

        boolean FirstEnd = true;
        for(int i=0; i<words.length; i++){
            if(words[i].equals(target)) {
                FirstEnd = false;
                break;
            }
        }
        if(FirstEnd) return 0;
        
        visit = new HashSet<>();
        bfs(begin);
        return MinCnt;
    }
}
