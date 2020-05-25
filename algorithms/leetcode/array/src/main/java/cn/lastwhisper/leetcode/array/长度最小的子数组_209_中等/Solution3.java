package cn.lastwhisper.leetcode.array.长度最小的子数组_209_中等;

import org.junit.Assert;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 编号：209
     * -------------------------------------------------------------------
     * 思考：边界值一定要考虑取还是不取，为什么？
     * -------------------------------------------------------------------
     * 思路：滑动窗口
     *  （1）双指针 l、r 维护一个滑动窗口
     *  （2）窗口的左边界在数组范围内,则循环继续
     *  （3）r未到数组尾且 sum<s，需要动态扩展窗口右边界
     *  （4）sum>s 需要动态缩小窗口左边界
     *  （5）循环内每次找 sum >= s 时的最小长度
     * -------------------------------------------------------------------
     * 时间复杂度：O(n) // l 和 r 指针同时遍历了一遍数组 O(2n)
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        // 初始窗口 [l,r] 是一个空集，所以 r 初值为 -1，后面需要先加才能使用
        int l = 0, r = -1;
        int ans = Integer.MAX_VALUE, sum = 0;
        // 维护窗口，通过窗口内 sum 与 s 的关系，触发窗口的扩容或缩小
        // 窗口的左边界在数组范围内，说明窗口还能继续扩容或者缩小
        while (l < nums.length) {
            // 因为 r 先加再用所以要保证 r + 1 < nums.length
            if (r + 1 < nums.length && sum < s) {
                sum += nums[++r];
            } else {
                sum -= nums[l++];
            }
            if (sum >= s) {
                ans = Math.min(ans, (r - l + 1));
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution3().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}