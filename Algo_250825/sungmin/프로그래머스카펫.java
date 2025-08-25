import java.util.*;
class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        int[] answer = {};

        for(int h =1; h*h<=yellow ; h++){
            if(yellow%h!=0){
                continue;
            }
            int w = yellow/h;
            int W = w+2;
            int H = h+2;
            if(H*W == total){
                answer = new int[] {Math.max(W,H),Math.min(W,H)};
            }

        }
        return answer;
    }
}