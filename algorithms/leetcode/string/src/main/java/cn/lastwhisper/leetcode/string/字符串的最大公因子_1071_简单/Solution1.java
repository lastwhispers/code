package cn.lastwhisper.leetcode.string.字符串的最大公因子_1071_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n+logn)
     * 空间复杂度：O(n)
     */
    public String gcdOfStrings(String str1, String str2) {
        // 如果 str1 和 str2 拼接后等于 str2和 str1 拼接起来的字符串（注意拼接顺序不同），
        // 那么一定存在符合条件的字符串 X
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        Assert.assertEquals("ABC", solution.gcdOfStrings("ABCABC", "ABC"));
        //Assert.assertEquals("AB", solution.gcdOfStrings("ABABAB", "ABAB"));

    }
}