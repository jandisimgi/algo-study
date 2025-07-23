import java.util.*;
class Solution {
    public int solution(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int N = nums.length/2;
        for(int num : nums){
            set.add(num);
        }

        int min = Math.min(set.size(),N);

        return min;
    }
}