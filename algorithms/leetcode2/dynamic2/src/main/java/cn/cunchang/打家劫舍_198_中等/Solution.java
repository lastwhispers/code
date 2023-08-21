package cn.cunchang.打家劫舍_198_中等;

class Solution {
    public int rob(int[] nums) {
        return tryRob(0, nums);
    }

    /**
     * idx 偷窃的起始位置
     */
    public int tryRob(int idx, int[] nums) {
        if (idx >= nums.length) {
            return 0;
        }
        int max = 0, res = 0;
        // 对[idx,nums.length-1]每个位置进行偷窃
        for (int i = idx; i < nums.length; i++) {
            // 找当前位置可以偷窃的最佳金额
            int subMax = tryRob(i + 2, nums);
            res = nums[i] + subMax;
            max = Math.max(max, res);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{1,2,3,1}));
    }

}