package BOJ;

import java.util.Scanner;

public class P1309 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int mod = 9901;
        
        int empty = 1;    
        int top = 1, bottom = 1;   
        
        for (int i = 2; i <= N; i++) {
            int newEmpty = (empty + top + bottom) % mod;
            int newTop = (empty + bottom) % mod;
            int newBottom = (empty + top) % mod;
            
            empty = newEmpty;
            top = newTop;
            bottom = newBottom;
        }
        
        int total = (empty + top + bottom) % mod;
        System.out.println(total);
    }
}
