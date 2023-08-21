package cn.lastwhisper.leetcode.array.移动零_283_简单;


import java.util.Arrays;

public class Solution2 {
    /**
     * 题目地址：https://leetcode.cn/problems/move-zeroes/
     * 编号：283
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void moveZeroes(int[] nums) {
        // k保证从[0,...,k)均为非零元素
        int k = 0;
        // 遍历到第i个元素后，保证[0,...,i]中所有非零元素，
        // 都按顺序排列在[0,...,k)中
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        // 同时保证[k,...,nums.length]中均为零元素
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        new Solution2().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}
