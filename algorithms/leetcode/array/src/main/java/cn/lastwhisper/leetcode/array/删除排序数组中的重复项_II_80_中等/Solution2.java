package cn.lastwhisper.leetcode.array.删除排序数组中的重复项_II_80_中等;

import org.junit.Assert;

public class Solution2 {
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
        // Initialize the counter and the second pointer.
        int j = 1, count = 1;

        // Start from the second element of the array and process
        // elements one by one.
        for (int i = 1; i < nums.length; i++) {

            // If the current element is a duplicate, increment the count.
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                // Reset the count since we encountered a different element
                // than the previous one.
                count = 1;
            }
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        Assert.assertEquals(7, new Solution2().removeDuplicates(nums));
    }

}