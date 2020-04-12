package cn.lastwhisper.leetcode.array.区间合并.矩形重叠_836_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *   检查重叠的矩阵区域
     * -------------------------------------------------------------------
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //相交矩阵的水平边
        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&
                //相交矩阵的垂直边
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }

    public static void main(String[] args) {
        int[] rec1 = {0, 0, 2, 2}, rec2 = {1, 1, 3, 3};
        Assert.assertTrue(new Solution2().isRectangleOverlap(rec1, rec2));
    }
}