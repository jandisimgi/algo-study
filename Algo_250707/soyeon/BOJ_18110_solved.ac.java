import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		double n1 = n*0.15;
		double num = Math.round(n1);
		int num1 = (int) num;
	    int sum=0;
	    
	    for(int i=num1; i<n-num1; i++) {
	    	sum+=arr[i];
	    }
	    int ans;
	    if((n-2*num1)<=0) {
	    	ans = 0;
	    }else {
	    	ans = (int) Math.round((double) sum / (n - 2 * num1));
	    }
	   
	    System.out.println(ans);
	    
	}
}
