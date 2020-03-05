package cn.lastwhisper.offer.面试题19_正则表达式匹配;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
     * -------------------------------------------------------------------
     * 思考：
     *  p的第二个字符是"*"
     * 一、p跳过两个字符
     * 二、text和p第一个字符匹配的前提下，text跳过第一个字符
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O()
     * 空间复杂度：O()
     */
    public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        // text="a"，p="a"或者"."都匹配
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        // p的第二个字符是"*"
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            //  一、跳过p第一个字符和第二个"*"，因为"*"可以匹配0个字符，示例：text="aaa"，p="ab*ac*a"
            return (isMatch(text, pattern.substring(2)) ||
                    //  二、text和p第一个字符匹配的前提下，text继续向下匹配，示例：text="aa"，p="a"
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            // text和p第一个字符匹配的前提下，p的第二个字符不是"*"，继续匹配
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }


    public static void main(String[] args) {
        String text = "a", pattern = "c*a";
        //String text = "aaa", pattern = "a.a";
        //String text = "aaa", pattern = "ab*ac*a";
        System.err.println(new Solution1().isMatch(text, pattern));
    }

}