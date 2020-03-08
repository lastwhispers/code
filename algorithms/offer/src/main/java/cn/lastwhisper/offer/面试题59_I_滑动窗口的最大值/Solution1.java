package cn.lastwhisper.offer.面试题59_I_滑动窗口的最大值;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：单调队列
     *
     *
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return nums;

        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            int index = i + 1 - k;
            if (!deque.isEmpty() && deque.peekFirst() < index) {
                deque.removeFirst();//deque最大值不在滑动窗口中
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();//新进入窗口的值大于deque最小值
            }
            deque.addLast(i);
            if (index >= 0) {//第一个窗口装满
                ans[index] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(new Solution1().maxSlidingWindow(nums, k)));
    }
}