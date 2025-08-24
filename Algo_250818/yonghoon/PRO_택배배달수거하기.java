import java.util.*;
import java.io.*;

class Solution {
    static long ans = 0;
    static int stored;
    static int storep;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        stored = deliveries.length-1;
        storep = pickups.length-1;
        
        while (true) {
            
            int d = check(deliveries, 'd');
            int p = check(pickups, 'p');   
            if (d == -1 && p == -1) break;   

     
            if (d < p) {    
                ans += 2*(p+1);
                sub(deliveries, d, cap);
                sub(pickups, p, cap);
            } else {
                ans += 2*(d+1);
                sub(deliveries, d, cap);
                sub(pickups, p, cap);
            }
            //System.out.println(ans);
        }
        
        return ans;
    }
    
    public int check (int [] arr, char c) {
        if (c == 'd') {
             for (int i = stored; i>=0; i--) {
                if (arr[i] != 0) {
                    stored = i;
                    return i;
                }
             }     
        } else {
             for (int i = storep; i>=0; i--) {
                if (arr[i] != 0) {
                    storep = i;
                    return i;
                }
             }   
        }
       
        return -1;
    }   
    
    public void sub(int [] arr, int d, int k) {
        while (true) {
            if (d == -1) {
                break;
            }
            
            arr[d] -= k;
            if (arr[d] >= 0) {
                break;
            } else {
                int remains = -arr[d];
                arr[d] = 0;
                k = remains;
                d--;
            }
        }
    }
}