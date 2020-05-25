package cn.lastwhisper.leetcode.array.长度最小的子数组_209_中等;

import org.junit.Assert;

class Solution6 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 编号：209
     * -------------------------------------------------------------------
     * 思考：边界值一定要考虑取还是不取，为什么？
     * -------------------------------------------------------------------
     * 思路：滑动窗口
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
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
        // 窗口的右边界在数组范围内，说明窗口还能继续扩容或者缩小
        while (r + 1 < nums.length) {
            // 因为 r 先加再用所以 r < nums.length - 1
            while (r + 1 < nums.length && sum < s) {
                r++;
                sum += nums[r];
            }
            if (sum >= s) {
                ans = Math.min(ans, (r + 1 - l));
            }
            while (l < nums.length && sum >= s) {
                sum -= nums[l];
                l++;
                if (sum >= s) {
                    ans = Math.min(ans, (r + 1 - l));
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        return ans;
    }

    public static void main(String[] args) {
        Assert.assertEquals(2, new Solution6().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}