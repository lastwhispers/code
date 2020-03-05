package cn.lastwhisper.leetcode.week.one178.有多少小于当前数字的数字_5344;

class Solution {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] < nums[i]) {
                    count++;
                }
            }
            arr[i] = count;
        }
        return arr;
    }
    
}