package cn.lastwhisper.leetcode.dynamic.打家劫舍_198_简单.recursion;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/house-robber/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归
     * -------------------------------------------------------------------
     * 时间复杂度：O()
     * 空间复杂度：O()
     */
    public int rob(int[] nums) {
        return tryRob(0, nums);
    }

    public int tryRob(int index, int[] nums) {
        if (index >= nums.length) {
            return 0;
        }
        int max = 0, res;
        for (int i = index; i < nums.length; i++) {
            res = nums[i] + tryRob(i + 2, nums);
            System.out.println("结果值：" + res);
            max = Math.max(max, res);
        }

        return max;
    }

    public static void main(String[] args) {
        //System.out.println(new Solution1().rob(new int[]{2, 1, 1, 2}));
        System.out.println(new Solution1().rob(new int[]{2, 7, 9, 3, 1}));
        //System.out.println(new Solution1().rob(new int[]{1, 2, 3, 1}));
    }
}