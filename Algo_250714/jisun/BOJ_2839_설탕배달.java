package 그리디;

import java.util.Scanner;

//완전 그리디는 아니고 그리디적 완탐

public class BOJ_2839_설탕배달 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); 
        
        int answer = -1;

        // 5kg 봉지를 가능한 최대 개수부터 줄여가며 시도
        for (int five = N / 5; five >= 0; five--) {
            int remaining = N - (5 * five);

            // 남은 무게가 3으로 나누어떨어지면 정답
            if (remaining % 3 == 0) {
                int three = remaining / 3;
                answer = five + three;
                break; // 최소 개수를 구했으므로 바로 종료
            }
        }

        System.out.println(answer);
    }
}
