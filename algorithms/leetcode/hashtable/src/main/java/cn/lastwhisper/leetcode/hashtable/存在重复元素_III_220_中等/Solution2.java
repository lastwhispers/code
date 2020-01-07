package cn.lastwhisper.leetcode.hashtable.存在重复元素_III_220_中等;

import java.util.TreeSet;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-iii/
     * 题意：nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：数组、无序、所有整数(long)
     *     输出：boolean
     * -------------------------------------------------------------------
     * 思路：在思路1中维护的滑动窗口中，找到满足t，需要线性搜索耗时O(o)
     *  可以优化为维护一个BST，BST搜索耗时O(log(n))
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*log(min(k,n)))
     * 空间复杂度：O(min(k,n))
     * -------------------------------------------------------------------
     * 执行用时 :44 ms, 在所有 Java 提交中击败了25.79%的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了89.89%的用户
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Long s = set.ceiling((long) nums[i]);//返回大于或等于给定键值的最小键值
            if (s != null && s - nums[i] <= t) return true;

            // Find the predecessor of current element
            Long g = set.floor((long) nums[i]);//返回小于或等于给定键值的最大键值
            if (g != null && nums[i] - g <= t) return true;

            set.add((long) nums[i]);
            if (set.size() > k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1};
        int k = 1, t = 2;//true

        //int[] nums = {1,2,3,1};//true
        //int k = 3, t = 0;
        //int[] nums = {1, 5, 9, 1, 5, 9};
        //int k = 2, t = 3;//false

        System.out.println(new Solution2().containsNearbyAlmostDuplicate(nums, k, t));
    }
}