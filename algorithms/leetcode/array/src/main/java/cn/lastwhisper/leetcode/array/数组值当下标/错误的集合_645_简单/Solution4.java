package cn.lastwhisper.leetcode.array.数组值当下标.错误的集合_645_简单;

import java.util.Arrays;

class Solution4 {
    /**
     * https://leetcode-cn.com/problems/set-mismatch/
     * -------------------------------------------------------------------
     * 数据特征：
     *  输入：数组、1~n整数、无序、存在重复数据、不一定包含丢失数据
     *  输出：数组[重复数据,丢失数据]、1~n+1整数、
     * -------------------------------------------------------------------
     * 思路：将数组中的每一个值当做下标，按下标-1找到数组对应的值，并更新为负数，
     *  在更新之前检查这个值是否已经是负数，如果已经是负数，说明这个值是重复数据。
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 相似题目：https://leetcode-cn.com/problems/first-missing-positive/
     */
    public int[] findErrorNums(int[] nums) {
        // 丢失数据初始值为1，
        int duplicate = -1, miss = 1;
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                // 重复出现的下标
                duplicate = Math.abs(num);
            } else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                miss = i + 1;
            }
        }
        return new int[]{duplicate, miss};
    }

    public static void main(String[] args) {
        // example
        //int[] arr = {1, 2, 2, 4};//2,3
        //int[] arr = {2, 2};//2,1
        // error example
        //int[] arr = {3, 2, 2};//2,1
        //int[] arr = {1, 1};//1,2
        //int[] arr = {1, 5, 3, 2, 2, 7, 6, 4, 8, 9};//10
        //int[] arr = {2, 3, 2};
        int[] arr = {3,3,1};
        System.out.println(Arrays.toString(new Solution4().findErrorNums(arr)));
    }
}