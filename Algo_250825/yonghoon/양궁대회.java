import java.util.*;
import java.io.*;

class Pos {
    String result;
    public Pos(String result) {
        this.result = result;
    }
}

class Solution {
    static int n1;
    static int[] info1;
    static int max = -1;
    static int[] answer;
    
    public int[] solution(int n, int[] info) {
        n1 = n;
        info1 = info;
        answer = new int[11];
        bfs();
        System.out.println(max);
        if (max == -1) {
            int[] minus = {-1};
            return minus;
        }
        return answer;
    }
    
    public void bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(""));
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                Pos p1 = q.poll();
                if (p1.result.length() == 11) {
                    check(p1.result);
                    continue;
                }
                String str1 = p1.result + "0";
                String str2 = p1.result + "1";
                q.offer(new Pos(str1));
                q.offer(new Pos(str2));
            }
        }
    }
    
    public void check(String result) {
        int cnt = n1;
        int score = 0;
        int partans = 0;
        int[] arr = new int[11];
        for (int i = 0; i <= 10; i++) {
            char type = result.charAt(i);
            if (type == '1') {
                int v = info1[i];
                if (cnt - (v + 1) >= 0) {
                    cnt -= (v + 1);
                    score += (10 - i);
                    arr[i] = (v + 1);
                } else {
                    return;
                }
            } else {
                if (info1[i] != 0) {
                    partans += (10 - i);
                }
            }
            if (i == 10 && cnt != 0) {
                arr[10] += cnt;
            }
        }
        if (score > partans) {
            checkTotal(arr, score - partans);
        }
    }
    
    public void checkTotal(int[] arr, int diff) {
        if (diff > max) {
            max = diff;
            for (int i = 0; i < 11; i++) {
                answer[i] = arr[i];
            }
        } else if (diff == max) {
            for (int i = 10; i >= 0; i--) {
                if (arr[i] != 0 || answer[i] != 0) {
                    if (arr[i] > answer[i]) {
                        answer = arr;
                    } else if (arr[i] < answer[i]) {
                    } else {
                        continue;
                    }
                    break;
                }
            }
        }
    }
}
