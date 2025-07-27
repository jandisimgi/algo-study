import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        //맵을 선언하기 
        Map<String, Integer>map = new HashMap<>();
        int answer = 0;
        for(String[]cloth : clothes){
            //배열의 두번째 애들이 옷의 type임
            String type = cloth[1];
            // 이제 map에 배치할 때가 되었음 만약에 해당 type에 해당하는 값이 없으면 0에서 시작한다음 +1을 함 
            map.put(type,map.getOrDefault(type,0)+1);
        }
        //위에까지 하면 이제 type 별로 개수가 나옴 
        //조합의 개수 +1(아무것도 착용하지 않은 것)들끼리의 곱 -1(모두 안입었을 경우)

        int sums = 1 ;
        for(int sum : map.values()){
            sums *= sum+1;
        }

        answer = sums-1;


        return answer;
    }
}