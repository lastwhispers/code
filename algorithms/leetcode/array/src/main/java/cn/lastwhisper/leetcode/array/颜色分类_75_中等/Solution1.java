package cn.lastwhisper.leetcode.array.颜色分类_75_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/sort-colors/
     * 编号：75
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：只有0,1,2
     * -------------------------------------------------------------------
     * 思路：
     *   计数排序，统计每一个数出现的频率
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void sortColors(int[] nums) {
        // freq 统计0、1、2的频率
        int[] freq = new int[3];
        for (int num : nums) {
            freq[num]++;
        }
        int idx = 0;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i]-- > 0) {
                nums[idx++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        new Solution1().sortColors(arr);
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }

}