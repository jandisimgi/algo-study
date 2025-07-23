import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        //해시맵 선언
        HashMap <String,Integer> map = new HashMap<>();
        //맵에 참가자 이름과 value에는 1값을 넣어주자 
        for(String name : participant){
            // 값이 없으면 0으로 만들고 1더하기 (동명이인있어서 그럼)
            map.put(name,map.getOrDefault(name,0)+1);
        }

        //이제 completion에서 돌면서 차감하자
        for(String name : completion){
            map.put(name,map.get(name)-1);
        }
        //0이 아닌 애들은 완주 못한 애들임
        for(String name : map.keySet()){
            if(map.get(name)!=0){
                return name;
            }
        }

        return "";


    }

}