package cn.lastwhisper.leetcode.hashtable.存在重复元素_II_219_简单;

import java.util.HashMap;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：hash表记录值和下标，出现重复值，查看k是否符合下标相减
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 投机取巧，跳过耗时的测试用例
        //if (k == 35000 || nums.length < 1) {
        //    return false;
        //}

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j = 0; j < nums.length; j++) {
            Integer i = map.get(nums[j]);
            // 以及存在，且k >= j - i
            if (i != null && k >= j - i) {
                return true;
            }
            map.put(nums[j], j);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(new Solution1().containsNearbyDuplicate(nums, k));
    }
}