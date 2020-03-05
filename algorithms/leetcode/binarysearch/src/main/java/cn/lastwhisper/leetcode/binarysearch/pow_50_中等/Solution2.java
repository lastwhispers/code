package cn.lastwhisper.leetcode.binarysearch.pow_50_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        //System.err.println(new Solution2().myPow(2.00000, 2));
        //System.err.println(new Solution2().myPow(2.00000, -2));
        //System.err.println(new Solution2().myPow(2.00000, 0));
        //System.err.println(new Solution2().myPow(0, 0));
        //System.err.println(new Solution2().myPow(-1, 0));
        //System.err.println(new Solution2().myPow(2.00000, 10));
        System.err.println(new Solution2().myPow(2.00000, -2147483648));
    }
}