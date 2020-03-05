package cn.lastwhisper.offer.面试题17_打印从1到最大的n位数_简单;

import java.util.Arrays;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  如何构造n个9
     *   一、9循环*10+9
     *   二、pow(10,n)-1
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int[] printNumbers(int n) {

        int base = 9;
        for (int i = 1; i < n; i++) {
            base *= 10;
            base += 9;
        }

        int[] nums = new int[base];
        for (int i = 0; i < base; i++) {
            nums[i] = i + 1;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.err.println(Arrays.toString(new Solution1().printNumbers(1)));
        System.err.println(Arrays.toString(new Solution1().printNumbers(2)));
    }
}