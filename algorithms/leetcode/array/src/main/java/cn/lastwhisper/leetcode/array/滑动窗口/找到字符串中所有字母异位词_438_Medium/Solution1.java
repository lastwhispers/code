package cn.lastwhisper.leetcode.array.滑动窗口.找到字符串中所有字母异位词_438_Medium;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
     * 核心思想：滑动窗口
     * 时间复杂度：O(n^2)
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        // 过滤肯定不符合规则的数据
        if (s == null || s.length() < p.length()) {
            return list;
        }
        int l = 0, r = 0;
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        while (l < sChar.length && r < sChar.length) {
            // [l,r]不满足异位词
            if (!isAnagram(sChar, l, r, pChar)) {
                // [l,r]不满足异位词且窗口大小不满足，扩展右边界
                if ((r - l) != pChar.length) {
                    r++;
                } else {
                    // [l,r]不满足异位词且窗口大小满足，扩展左边界
                    l++;
                }
            } else {
                // [l,r]满足异位词，扩展左边界
                list.add(l);
                l++;
            }
        }
        return list;
    }

    public boolean isAnagram(char[] sChar, int start, int end, char[] pChar) {
        if ((end - start + 1) != pChar.length) {
            return false;
        }
        byte[] freq = new byte[26];
        for (int i = start; i <= end; i++) {
            freq[sChar[i] - 'a']++;
        }

        for (int i = 0; i < pChar.length; i++) {
            freq[pChar[i] - 'a']--;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        new Solution1().findAnagrams("cbaebabacd", "abc").forEach(System.out::print);
        System.out.println();
        new Solution1().findAnagrams("abab", "ab").forEach(System.out::print);
        System.out.println();
        new Solution1().findAnagrams("", "a").forEach(System.out::print);//错误数据

        //System.out.println(new Solution1().isAnagram(new char[]{'c','b','a'}, new char[]{'a','b','c'}));

    }

}