import java.io.*;
import java.util.*;

class Solution {
    
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < moves.length; i++) {
            int status = moves[i]; // 열
            
            int toyRow = -1;
            int toy = -1; // 장난감 번호
            for (int j = 0; j < board.length; j++) { // 행
                if (board[j][status-1] != 0) {
                    toy = board[j][status-1];
                    toyRow = j;
                    board[j][status-1] = 0;
                    break;
                }
            }
            
            if (toyRow != -1) {
                if (stack.isEmpty()) {
                    stack.push(toy);
                } else {
                    if (stack.peek() == toy) {
                        stack.pop();
                        answer++;
                    } else {
                        stack.push(toy);
                    }
                        
                }
            }
        }

        return answer*2;
    }
}