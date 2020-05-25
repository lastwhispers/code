package cn.lastwhisper.leetcode.array.删除排序数组中的重复项_II_80_中等;

import org.junit.Assert;

public class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
     * 编号：80
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：从小到大有序
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //  区间[0,...,pos]每个元素最多出现两次
        int pos = 0;
        //  数字可出现的次数
        int frequency = 2;
        for (int num : nums) {
            if (pos < frequency || num > nums[pos - frequency]) {
                nums[pos++] = num;
            }
        }
        return pos;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        Assert.assertEquals(7, new Solution1().removeDuplicates(nums));
    }

}