package cn.lastwhisper.leetcode.dynamic.戳气球_312_困难;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/burst-balloons/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归回溯
     * -------------------------------------------------------------------
     * 时间复杂度：O(n!)
     * 空间复杂度：O(n!)
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        List<Integer> arrayList = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        return helper(arrayList, 0);
    }

    private int helper(List<Integer> nums, int coins) {
        if (nums.size() == 0) {
            return coins;
        }
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            int tmp = nums.get(i);
            int delta = nums.get(i)
                    * (i - 1 < 0 ? 1 : nums.get(i - 1))
                    * (i + 1 > nums.size() - 1 ? 1 : nums.get(i + 1));
            nums.remove(i);
            res = Math.max(res, helper(nums, coins + delta));
            nums.add(i, tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        Assert.assertEquals(167, new Solution1().maxCoins(new int[]{3, 1, 5, 8}));
    }
}