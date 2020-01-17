package cn.lastwhisper.leetcode.other.四的幂_342_简单;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/power-of-four/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(1)
     * 空间复杂度：O(1)
     */
    //public boolean isPowerOfFour(int n) {
    //    if (n == 0) return false;
    //    while (n % 4 == 0) n /= 4;
    //    return n == 1;
    //}

    public boolean isPowerOfFour(int num) {
        return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
    }

    public static void main(String[] args) {
        int num = 16;
        System.out.println(new Solution1().isPowerOfFour(num));
    }
}