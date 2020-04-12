package cn.lastwhisper.leetcode.string.面试题01_06_字符串压缩_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/compress-string-lcci/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public String compressString(String S) {
        StringBuilder compress = new StringBuilder();
        int count = 1;
        char prev = ' ';
        for (char c : S.toCharArray()) {
            if (prev == c) {//重复
                count++;
            } else if (prev != ' ') {//遇到新的
                compress.append(count);
                count = 1;
            }
            if (count == 1) {//第一次出现
                compress.append(c);
            }
            prev = c;
        }
        compress.append(count);
        return compress.length() >= S.length() ? S : compress.toString();
    }

    public static void main(String[] args) {
        Assert.assertEquals("a2b1c5a3", new Solution1().compressString("aabcccccaaa"));
    }
}