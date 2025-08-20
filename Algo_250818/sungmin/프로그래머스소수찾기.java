import java.util.*;

class Solution {
    public int solution(String numbers) {
        Set<Integer> result = new HashSet<>();
        //숫자를 문자단위로 쪼개기
        char[] arr = numbers.toCharArray();
        //방문체크하는
        boolean[]visited = new boolean[arr.length];
        dfs(arr,visited,new StringBuilder(),result);
        //이렇게 나온 result set안에서 소수의 개수 구하면 됨
        int answer = 0;
        for(int i : result){
            if(isPrime(i)){
                answer++;
            }
        }

        return answer;
    }
    //dfs 순열 만드는 함수 
    private void dfs(char[]arr,boolean[]visited,StringBuilder sb ,Set<Integer>result){
        if(sb.length()>0){
            result.add(Integer.parseInt(sb.toString()));
        }

        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                //방문체크 true
                visited[i]= true;
                sb.append(arr[i]);
                dfs(arr,visited,sb,result);
                sb.deleteCharAt(sb.length()-1);
                visited[i] = false;

            }
        }
    }

    private boolean isPrime(int number){
        if(number == 1 || number == 0 ){
            return false;
        }
        if(number == 2){
            return true;
        }
        else if((number % 2) == 0){
            return false;
        }

        else {
            for(int i=3 ; i<number; i++){
                if(number%i == 0){
                    return false;
                }
            }
            return true;
        }

    }


    //소수인지 확인하는 함수
}