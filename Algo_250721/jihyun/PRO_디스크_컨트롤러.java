import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] != o2[2]) return o1[2] - o2[2]; 
                if (o1[1] != o2[1]) return o1[1] - o2[1]; 
                return o1[0] - o2[0];
            }
        });

        int currTime = 0, returnTimeSum = 0, jobIndex = 0; 
        int jobCount = jobs.length;

        while (jobIndex < jobCount || !pq.isEmpty()) {
            while (jobIndex < jobCount && jobs[jobIndex][0] <= currTime) {
                pq.add(new int[]{jobIndex, jobs[jobIndex][0], jobs[jobIndex][1]});
                jobIndex++;
            }

            if (!pq.isEmpty()) {
                int[] out = pq.poll(); 
                int reqTime = out[1]; 
                int useTime = out[2]; 

                currTime += useTime; 
                returnTimeSum += currTime - reqTime; 
            } else { 
                currTime = jobs[jobIndex][0];
            }
        }
        return returnTimeSum / jobCount; 
    }
}
