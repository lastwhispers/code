package cn.cunchang.二分查找_704_简单;

import org.junit.Assert;

class Solution {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1; // -1是因为最右边不存在的值会超界
        while (l <= r) {// =是因为该值有可能是正确答案
            int mid = (r + l) / 2;
            if (nums[mid] > target) {// 中间值大于目标值
                r = mid - 1; // -1是因为该值不符合要求
            } else if (nums[mid] < target) {
                l = mid + 1; // +1是因为该值不符合要求
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // 偶数数组 存在
//        Assert.assertEquals(4, new Solution().
//                search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        // 偶数数组 左边中间不存在
//        Assert.assertEquals(-1, new Solution().
//                search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
        // 偶数数组 最右边不存在
        Assert.assertEquals(-1, new Solution().
                search(new int[]{-1, 0, 3, 5, 9, 12}, 13));
        // 偶数数组 存在
//        Assert.assertEquals(4, new Solution().
//                search(new int[]{-1, 0, 3, 5, 9}, 9));
        // 偶数数组 不存在
//        Assert.assertEquals(-1, new Solution().
//                search(new int[]{-1, 0, 3, 5, 9}, 2));
    }
}