package cn.lastwhisper.leetcode.array.移除元素_27_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/min-stack/
     * 编号：27
     * -------------------------------------------------------------------
     * 思考：
     *  特殊场景2：数组中只有少量待删除数据
     *   num=[1，2，3，5，4]，Val=4
     *   num=[4，1，2，3，5]，Val=4
     * -------------------------------------------------------------------
     * 思路：
     *  快慢指针
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
        int i = 0;
        int n = nums.length - 1;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n];
                n--;
            } else {
                i++;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        //Assert.assertEquals(2, new Solution1().removeElement(new int[]{3, 2, 2, 3}, 3));
        Assert.assertEquals(2, new Solution2().removeElement(new int[]{2, 2, 3, 3}, 3));

    }
}