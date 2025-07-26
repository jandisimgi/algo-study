package BOJ;

import java.util.Scanner;

public class P1025 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int max = -1;
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
				 int sqrt = (int) Math.sqrt(arr[i][j]);
                 if (arr[i][j] >= 0 && sqrt * sqrt == arr[i][j]) {
                     max = Math.max(max, arr[i][j]);
                 }
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				for (int dr = - N + 1; dr < N; dr++) {
					for (int dc = - M + 1; dc < M; dc++) {
						if (dr == 0 && dc == 0) {
							continue;
						}
						int x = i;
                        int y = j;
                        StringBuilder sb = new StringBuilder();
                        
                        while (x >= 0 && x < N && y >= 0 && y < M) {
                            sb.append(arr[x][y]);
                            int num = Integer.parseInt(sb.toString());
                            int sqrt = (int) Math.sqrt(num);
                            if (num >= 0 && sqrt * sqrt == num) {
                                max = Math.max(max, num);
                            }

                            x += dr;
                            y += dc;
                        }
					}
				}
			}
		}
		System.out.println(max);
		
	}
}
