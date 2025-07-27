import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        // 들어오는 숫자가 문자열이기에 정렬하면 첫글자만 비교해서 정렬함

        Arrays.sort(phone_book);
        for(int i=0 ; i<(phone_book.length-1); i++){
            if(phone_book[i+1].startsWith(phone_book[i])){
                answer=false;
            }
        }

        return answer;
    }
}