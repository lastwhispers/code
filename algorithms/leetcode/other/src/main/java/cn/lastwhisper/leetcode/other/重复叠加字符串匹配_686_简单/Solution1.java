package cn.lastwhisper.leetcode.other.重复叠加字符串匹配_686_简单;

class Solution1 {
    /**
     * 本题：https://leetcode-cn.com/problems/repeated-string-match/
     * -------------------------------------------------------------------
     * 数据特征：
     *  输入：字符串、无序、26个小写字母
     *  输出：整数(-1或者叠加次数)
     * -------------------------------------------------------------------
     * 思路：循环叠加A，使用String API查看是否包含B，
     *  循环的终止条件为：
     *    （1）B.length() / A.length()
     *    （2）包含B
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     * 思考：lastIndexOf与indexOf要看数据的规律而是用，
     *  比如这里使用lastIndexOf比使用indexOf要快很多
     */
    public int repeatedStringMatch(String A, String B) {
        // B直接是A的子串
        if (A.lastIndexOf(B) != -1) return 1;

        /*
         * A需要自己叠加多次，才满足B直接是A的子串，
         *  这个叠加次数的限制满足 B.length() / A.length()
         */
        int frequency = B.length() / A.length();
        StringBuilder sb = new StringBuilder(A);
        for (int i = 2; i <= frequency + 2; i++) {
            sb.append(A);
            if (i >= frequency && sb.toString().lastIndexOf(B) != -1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        // exmaple
        //String A = "abcd";
        //String B = "cdabcdab";

        // error example
        //String A = "abc";
        //String B = "cabcabca";

        //String A = "abcabcabcabc";
        //String B = "abac";

        //String A = "aa";
        //String B = "a";

        //String A = "aaaaaaaaaaaaaaaaaaaaaab";
        //String B = "ba";

        String A = "a";
        String B = "aa";
        System.out.println(new Solution1().repeatedStringMatch(A, B));
    }
}