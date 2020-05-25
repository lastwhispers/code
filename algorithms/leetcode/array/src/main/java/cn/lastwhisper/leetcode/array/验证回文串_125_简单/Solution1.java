package cn.lastwhisper.leetcode.array.验证回文串_125_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/valid-palindrome/
     * 编号：125
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  对撞指针遍历比较
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public boolean isPalindrome(String s) {
        String upperS = s.toUpperCase();
        int l = 0, r = upperS.length() - 1;
        // 这里不能等于
        while (l < r) {
            while (l < r && !valid(upperS.charAt(l))) {
                l++;
            }
            while (l < r && !valid(upperS.charAt(r))) {
                r--;
            }
            if (upperS.charAt(l) != upperS.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    // 只考虑字母和数字字符
    private boolean valid(char c) {
        return ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122));
    }

    public static void main(String[] args) {
        //String s = "A man, a plan, a canal: Panama";
        //String s = "race a car";
        String s = "a";
        Assert.assertTrue(new Solution1().isPalindrome(s));
    }
}