import java.util.*;

class Solution {
    static int [] arr;
    static int [][] mail;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        
        int len = id_list.length;
        mail = new int[len][len+1];  // [len-1][len] -> 메일 갯수
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], i);
        }
        
        
        for (int i = 0; i < report.length; i++) {
            StringTokenizer st = new StringTokenizer(report[i], ",");
            StringTokenizer rt = new StringTokenizer(st.nextToken());
            
            String pitch = rt.nextToken();
            int num1 = map.get(pitch);
            String receiver = rt.nextToken();
            int num2 = map.get(receiver);
            mail[num1][num2] = 1;  
            
        }
        
        for (int i = 0; i < len; i++) {
            int ct = 0;
            for (int j = 0; j < len; j++) {
                if(mail[j][i] == 1) {
                    ct++;
                }
            }
            if (ct >= k) {
                for (int j = 0; j < len; j++) {
                    if(mail[j][i] == 1) {
                        mail[j][len]++;
                    }
                }
            }
        }
        
        arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = mail[i][len];
        }
                                       
        
        
        
        return arr;
    }
}