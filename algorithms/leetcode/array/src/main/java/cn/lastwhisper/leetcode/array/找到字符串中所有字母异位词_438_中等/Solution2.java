package cn.lastwhisper.leetcode.array.找到字符串中所有字母异位词_438_中等;

import java.util.ArrayList;
import java.util.List;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 编号：438
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（滑动窗口）
     *  （1）双指针 l、r 维护一个滑动窗口进行遍历主串 s，
     *       window[] hash 表用于记录 [l,r] 窗口中某字符次数，是否满足模式串 s 中字符次数，
     *       total 用于记录 [l,r] 窗口各个字符数量，是否满足模式串 s 中字符数量，
     *       need[] hash 表用于记录模式串 s 中各个字符数量，
     *  （2）
     *  （3）
     *  （4）
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null || s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        int l = 0, r = 0, total = p.length();
        int[] window = new int[26];
        int[] need = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }
        while (r < s.length()) {
            int cr = s.charAt(r) - 'a';
            // s 中的字符，是否存在 p 中
            // 如果存在，window 记录该字符出现次数，该字符出现次数小于等于 p 中该字符数，total--
            if (need[cr] > 0) {
                window[cr]++;
                if (window[cr] <= need[cr]) {
                    total--;
                }
            }
            // [l,r] 中包含了 p 中所有字符
            while (total == 0) {
                // 必须是和 p 的长度相同才可以算字母异位词
                // 比如 s=ceba p=abc 也满足 total==0 ，但是并不是异位词
                if (r - l == p.length() - 1) {
                    result.add(l);
                }
                // 缩小窗口，剔除 l 所指向的字符次数，total++
                int cl = s.charAt(l) - 'a';
                if (need[cl] > 0) {
                    window[cl]--;
                    if (window[cl] < need[cl]) {
                        total++;
                    }
                }
                l++;
            }
            r++;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        //String s = "abab", p = "ab";
        System.out.println(new Solution2().findAnagrams(s, p));
    }
}