import java.util.*;
class Solution {
    static String[] vowel = {"A","E","I","O","U"};
    static String[] result;
    static Set<String> wordSet = new TreeSet<>();
    
    private static void findWord(int depth){
        if(depth == result.length){
            String str = "";
            for(int i=0; i<depth; i++){
                str += result[i];
            }
            wordSet.add(str);
            return;
        }
        
        for(int i=0; i<vowel.length; i++){
            result[depth] = vowel[i];
            findWord(depth+1);
        }
        
    }
    public int solution(String word) {
        for(int i=1; i<=5; i++){
            result = new String[i];
            findWord(0);
        }
        int idx = 0;
        for(String findWord : wordSet){
            if(!findWord.equals(word)) idx++;
            else break;
        }
        return idx+1;
    }
}
