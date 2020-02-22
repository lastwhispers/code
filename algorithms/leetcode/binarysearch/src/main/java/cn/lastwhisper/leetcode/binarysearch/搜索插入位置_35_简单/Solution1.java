package cn.lastwhisper.leetcode.binarysearch.搜索插入位置_35_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/search-insert-position/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：线性遍历
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            // 起始,中间
            if (nums[i] >= target) {
                return i;
            }
        }
        // 最后
        return nums.length;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(new Solution1().searchInsert(nums, target));
    }
}