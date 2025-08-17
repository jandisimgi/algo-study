import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
      
        String[] arr = br.readLine().split("-");
        int result = 0;
        
     
        for(int i=0; i<arr.length; i++) {
  
            for (String num : arr[i].split("\\+")) {
                
                if(i==0) {
                    result += Integer.parseInt(num);
                }
              
                else{
                    result -= Integer.parseInt(num);
                }
            }
        }
        System.out.println(result);
    }
}
