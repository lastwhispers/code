package cn.cunchang.接雨水_42_困难;

import org.junit.Assert;

class Solution3 {
    /**
     * 题目地址：https://leetcode.cn/problems/container-with-most-water/
     * -------------------------------------------------------------------
     * 思考：只看局部柱子最多盛放水量
     * -------------------------------------------------------------------
     * 思路：备忘录
     *
     * 之前的暴力解法，不是在每个位置 i 都要计算 r_max 和 l_max 吗？我们直接把结果都提前计算出来，别傻不拉几的每次都遍历，这时间复杂度不就降下来了嘛。
     *
     * 我们开两个数组 r_max 和 l_max 充当备忘录，l_max[i] 表示位置 i 左边最高的柱子高度，r_max[i] 表示位置 i 右边最高的柱子高度。预先把这两个数组计算好，避免重复计算：
     *
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int trap(int[] height) {


        return 0;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        Assert.assertEquals(6, solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}