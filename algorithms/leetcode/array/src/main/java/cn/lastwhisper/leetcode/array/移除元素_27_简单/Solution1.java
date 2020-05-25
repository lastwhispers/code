package cn.lastwhisper.leetcode.array.移除元素_27_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/min-stack/
     * 编号：27
     * -------------------------------------------------------------------
     * 思考：
     *  特殊场景1：数组本身“有序”
     *   num=[1，1，1，1，1]，Val=1
     * -------------------------------------------------------------------
     * 思路：
     *  快慢指针
     *  1、记录val的索引slow
     *  2、遍历，找到非val的索引i后，交换i和pos位置的值
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeElement(int[] nums, int val) {
        // 特判
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 区间[0,...,slow)均为不等于val的元素，slow为val的索引
        int slow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                if (slow != i) { // 防止数组本身就是“有序”
                    nums[slow] = nums[i];
                }
                slow++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        //Assert.assertEquals(2, new Solution1().removeElement(new int[]{3, 2, 2, 3}, 3));
        Assert.assertEquals(2, new Solution1().removeElement(new int[]{2, 2, 3, 3}, 3));

    }
}