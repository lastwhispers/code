package cn.lastwhisper.leetcode.array.区间合并.矩形重叠_836_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  两个矩阵的重叠是无限的，但是不重叠是有限的
     *  检查位置
     * -------------------------------------------------------------------
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // 矩阵投影X轴，矩阵1在矩阵2左侧 或 矩阵1在矩阵2右侧
        boolean x_overlap = !(rec1[2] <= rec2[0] || rec2[2] <= rec1[0]);
        // 矩阵投影y轴，矩阵1在矩阵2上侧 或 矩阵1在矩阵2下侧
        boolean y_overlap = !(rec1[3] <= rec2[1] || rec2[3] <= rec1[1]);
        return x_overlap && y_overlap;
    }

    public static void main(String[] args) {
        int[] rec1 = {0, 0, 2, 2}, rec2 = {1, 1, 3, 3};
        Assert.assertTrue(new Solution1().isRectangleOverlap(rec1, rec2));
    }
}