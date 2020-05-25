package cn.lastwhisper.leetcode.array.反转字符串_344_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-string/
     * 编号：344
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：对撞指针交换元素
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char c = s[l];
            s[l] = s[r];
            s[r] = c;
            l++; r--;
        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        new Solution1().reverseString(s);
        Assert.assertArrayEquals(new char[]{'o', 'l', 'l', 'e', 'h'}, s);
    }
}