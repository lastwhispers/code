package cn.lastwhisper.leetcode.array.删除排序数组中的重复项_26_简单;


import org.junit.Assert;

public class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
     * 编号：26
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：从小到大有序
     * -------------------------------------------------------------------
     * 思路：
     *  双指针
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int removeDuplicates(int[] nums) {
        // 特判
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 区间[0,...,pos]均为第一次出现元素
        int pos = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[pos] != nums[i]) {
                nums[++pos] = nums[i];
            }
        }
        return pos + 1;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 2, 3, 4, 4};
        Assert.assertEquals(5, new Solution1().removeDuplicates(nums));
    }
}