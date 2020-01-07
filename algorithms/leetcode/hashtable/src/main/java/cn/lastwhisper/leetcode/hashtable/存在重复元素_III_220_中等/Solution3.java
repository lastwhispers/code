package cn.lastwhisper.leetcode.hashtable.存在重复元素_III_220_中等;

import java.util.HashMap;
import java.util.Map;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-iii/
     * 题意：nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：数组、无序、所有整数(long)
     *     输出：boolean
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     * -------------------------------------------------------------------
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the nei***or buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in nei***or buckets
            d.put(m, (long) nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1};
        int k = 1, t = 2;//true

        //int[] nums = {1,2,3,1};//true
        //int k = 3, t = 0;
        //int[] nums = {1, 5, 9, 1, 5, 9};
        //int k = 2, t = 3;//false

        System.out.println(new Solution3().containsNearbyAlmostDuplicate(nums, k, t));
    }
}