class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        int n = nums.length;

        for(int num : nums){
            int digit = 0;
            while(num > 0){
                num = num / 10;
                digit += 1;
            }

            if(digit % 2 == 0){
                count += 1;
            }
        }

        return count;
    }
}