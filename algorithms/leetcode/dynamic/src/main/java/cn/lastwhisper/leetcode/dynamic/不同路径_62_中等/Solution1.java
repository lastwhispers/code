package cn.lastwhisper.leetcode.dynamic.不同路径_62_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归(自顶向下)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    public int uniquePaths(int m, int n) {
        // m==1||n==1时，只剩下一条路可走，直接返回1
        if (m == 1 || n == 1) {
            return 1;
        }
        // 向右走+向下走
        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().uniquePaths(7, 3));
    }
}