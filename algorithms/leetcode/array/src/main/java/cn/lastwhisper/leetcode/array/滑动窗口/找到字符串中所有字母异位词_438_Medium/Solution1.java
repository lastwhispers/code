package cn.lastwhisper.leetcode.array.找到字符串中所有字母异位词_438_Medium;

import java.util.List;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 核心思想：快慢指针
     */
    public  List<Integer> findAnagrams(String s, String p) {
        int l = 0, r = 0;

        return null;
    }


    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> list = new Solution1().findAnagrams(s, p);
        System.out.println(list.toString());

    }

}