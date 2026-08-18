import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51];

        for (int i = 0; i <= nums.length - k; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            for (int x : seen) {
                count[x]++;
            }
        }
        int answer = -1;

        for (int x = 0; x <= 50; x++) {
            if (count[x] == 1) {
                answer = x;
            }
        }

        return answer;
    }
}