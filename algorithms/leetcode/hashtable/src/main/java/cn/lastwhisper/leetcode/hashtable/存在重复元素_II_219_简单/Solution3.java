package cn.lastwhisper.leetcode.hashtable.存在重复元素_II_219_简单;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：维持一个大小为k的窗口，查看窗口中存在重复元素(滑动窗口解法)
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(new Solution3().containsNearbyDuplicate(nums, k));
    }
}