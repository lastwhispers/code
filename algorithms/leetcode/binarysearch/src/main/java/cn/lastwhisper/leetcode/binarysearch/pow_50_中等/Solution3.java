package cn.lastwhisper.leetcode.binarysearch.pow_50_中等;

class Solution3 {
    public double myPow(double x, int n) {
        // 指数-偶数
        if (n == 0) {
            return 1;
        }
        // 指数-奇数
        if (n == 1) {
            return x;
        }
        // 指数-负数
        if (n == -1) {
            return 1 / x;
        }
        double half = myPow(x, n / 2);//n>>1
        double base = myPow(x, n % 2);
        return half * half * base;
    }

    public static void main(String[] args){
        System.err.println(new Solution3().myPow(34.00515,-3));
    }
}