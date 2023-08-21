package cn.cunchang.打家劫舍_198_中等;

import java.util.Arrays;

class Solution2 {
    public int rob(int[] nums) {
        int[] moment = new int[nums.length];
        Arrays.fill(moment, -1);
        return tryRob(0, nums, moment);
    }

    /**
     * idx 偷窃的起始位置
     */
    public int tryRob(int idx, int[] nums, int[] moment) {
        if (idx >= nums.length) {
            return 0;
        }
        if (moment[idx] != -1) {
            return moment[idx];
        }
        int max = 0, res = 0;
        // 对[idx,nums.length-1]每个位置进行偷窃
        for (int i = idx; i < nums.length; i++) {
            // 找当前位置可以偷窃的最佳金额
            int subMax = tryRob(i + 2, nums, moment);
            res = nums[i] + subMax;
            max = Math.max(max, res);
        }
        moment[idx] = max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().rob(new int[]{1, 2, 3, 1}));
    }

}