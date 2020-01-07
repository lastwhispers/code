package cn.lastwhisper.leetcode.hashtable.存在重复元素_II_219_简单;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：维持一个大小为k的队列，在队列大小中存在重复元素
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 投机取巧，跳过耗时的测试用例
        //if (k == 35000 || nums.length < 1) {
        //    return false;
        //}

        Queue<Integer> queue = new ArrayDeque<>();
        for (int num : nums) {
            if (queue.contains(num)) {
                return true;
            }
            queue.add(num);
            // 维持队列大小
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(new Solution2().containsNearbyDuplicate(nums, k));
    }
}