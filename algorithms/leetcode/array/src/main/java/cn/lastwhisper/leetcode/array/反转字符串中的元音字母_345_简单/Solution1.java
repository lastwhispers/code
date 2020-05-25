package cn.lastwhisper.leetcode.array.反转字符串中的元音字母_345_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
     * 编号：345
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：对撞指针条件交换元素
     *  元音字母：a,e,i,o,u 大小写
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public String reverseVowels(String s) {
        int l = 0, r = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            while (l < r && !valid(s.charAt(l))) {
                l++;
            }
            while (l < r && !valid(s.charAt(r))) {
                r--;
            }
            if (l < r) {// 需要判断
                char c = chars[l];
                chars[l] = chars[r];
                chars[r] = c;
                l++;
                r--;
            }
        }
        return new String(chars);
    }

    private boolean valid(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        Assert.assertEquals("holle", new Solution1().reverseVowels("hello"));
        Assert.assertEquals("leotcede", new Solution1().reverseVowels("leetcode"));
    }
}