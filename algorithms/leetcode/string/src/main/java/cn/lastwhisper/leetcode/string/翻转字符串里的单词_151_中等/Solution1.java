package cn.lastwhisper.leetcode.string.翻转字符串里的单词_151_中等;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-words-in-a-string/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public String reverseWords(String s) {
        s = s.trim();
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length / 2; i++) {
            String temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (String s1 : arr) {
            if(!"".equals(s1)){
                sb.append(s1);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Assert.assertEquals("example good a", new Solution1().reverseWords("a good   example"));
    }
}