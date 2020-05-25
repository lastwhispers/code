package cn.lastwhisper.leetcode.array.验证回文串_125_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/valid-palindrome/
     * 编号：125
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  对撞指针遍历比较
     *  统一转成大写：ch & 0b11011111 简写：ch & 0xDF
     *  统一转成小写：ch | 0b00100000 简写：ch | 0x20
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {// 这里不能等于
            while (l < r && !valid(s.charAt(l))) l++;
            while (l < r && !valid(s.charAt(r))) r--;
            if ((s.charAt(l++) & 0xDF) != (s.charAt(r--) & 0xDF)) return false;
        }
        return true;
    }

    // 只考虑字母和数字字符
    private boolean valid(char c) {
        return ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122));
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        //String s = "race a car";
        //String s = "a";
        Assert.assertTrue(new Solution2().isPalindrome(s));
    }
}