import java.util.*;
import java.io.*;
class Solution {
    static int [] lottos1;
    static int [] win_nums1;
    
    static int ans = 0; 
    
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        lottos1 = lottos;
        win_nums1 = win_nums;

        int ct = 0; 
        
        for (int i = 0; i < lottos1.length; i++) {
            if (lottos1[i] != 0) {
                ct++;
                check(lottos1[i]);
            }
        }
        
        ct = 6 - ct; 
        
        System.out.println(ans);
        
        if (ans == 6) {
            answer[1] = 1;
        } else if (ans == 5) {
            answer[1] = 2; 
        } else if (ans == 4) {
            answer[1] = 3; 
        } else if (ans == 3) {
            answer[1] = 4; 
        } else if (ans == 2) {
            answer[1] = 5; 
        } else {
            answer[1] = 6; 
        } 

        ans += ct;
        System.out.println(ans);
        
        if (ans == 6) {
            answer[0] = 1;
        } else if (ans == 5) {
            answer[0] = 2; 
        } else if (ans == 4) {
            answer[0] = 3; 
        } else if (ans == 3) {
            answer[0] = 4; 
        } else if (ans == 2) {
            answer[0] = 5; 
        } else {
            answer[0] = 6; 
        } 
        
        return answer;
    }
    
    public void check(int num) {
        for (int i = 0; i < win_nums1.length; i++) {
            if (win_nums1[i] == num) {
                ans++;
                return;
            }
        }
    }
}