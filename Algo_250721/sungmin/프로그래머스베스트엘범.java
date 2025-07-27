import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map <String,Integer> map = new HashMap<>();
        //일단 장르별로 묶기도해야하고 같은 장르끼리 아이디값대로 묶기도 해야한다는게 핵심임
        for(int i =0 ; i< genres.length; i++){
            map.put(genres[i],map.getOrDefault(genres[i],0)+plays[i]);
        }
        // 위에서는 장르 간 크기를 구해놓은거임 이제 장르안에서 구해야함
        Map <String,List<int[]>> sm = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int id = i;
            int play = plays[i];
            sm.putIfAbsent(genre,new ArrayList<>());
            //기존에 있는 배열을 갈아끼는게 아니라 추가하는것이기에 key에 해당하는 value를 찾고 
            //거기에 add하는것임 
            sm.get(genre).add(new int[]{id,play});


        }

        List<String> genrenew = new ArrayList<>(map.keySet());
        genrenew.sort((a, b) -> map.get(b) - map.get(a));

        List<Integer> result = new ArrayList<>();
        for (String genre : genrenew) {
            List<int[]> songList = sm.get(genre);

            //아 오름차순 이거 람다식 적용시킴
            songList.sort((a, b) -> {
                //plays 값이 같으면 id 값으로
                if (b[1] == a[1]) return a[0] - b[0];
                return b[1] - a[1];
            });

            result.add(songList.get(0)[0]);
            if (songList.size() > 1) {
                result.add(songList.get(1)[0]);
            }
        }

        //리스트 배열 전환해야함 
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}