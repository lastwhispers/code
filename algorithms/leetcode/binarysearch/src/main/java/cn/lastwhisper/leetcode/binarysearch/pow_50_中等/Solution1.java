package cn.lastwhisper.leetcode.binarysearch.pow_50_中等;


class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  一、n=-2147483648无法处理
     *  二、二分优化n^8=n^4^2=n^2^2^2
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        // x底数 n指数
        double result;

        int absExponent = n;
        if (n < 0) absExponent = -n;
        result = pow(x, absExponent);

        if (n < 0) {
            result = 1.0 / result;
        }

        return result;
    }

    public double pow(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        //System.err.println(new Solution1().myPow(2.00000, 2));
        //System.err.println(new Solution1().myPow(2.00000, -2));
        //System.err.println(new Solution1().myPow(2.00000, 0));
        System.err.println(new Solution1().myPow(0, 0));
        System.err.println(new Solution1().myPow(-1, 0));
        System.err.println(new Solution1().myPow(2.00000, 10));
    }
}