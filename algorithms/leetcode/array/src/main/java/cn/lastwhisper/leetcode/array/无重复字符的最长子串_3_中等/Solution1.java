package cn.lastwhisper.leetcode.array.无重复字符的最长子串_3_中等;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 编号：3
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（滑动窗口）
     *  （1）左指针未超出边界，窗口继续扩容或缩小
     *  （2）Hash表每次检查右指针指向的值是否存在
     *  （3）存在则，移除该字符（移除标识），窗口长度减一
     *  （4）不存在，标识该字符已经存在，窗口长度加一
     * -------------------------------------------------------------------
     * 时间复杂度：O(n) // 字符串的长度
     * 空间复杂度：O(1) // 窗口长度
     */
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        // 窗口 [l,r] 内无重复元素
        int l = 0, r = 0;
        int res = 0;
        while (l < s.length()) {
            if (r <  s.length()  && freq[s.charAt(r)] == 0) {
                freq[s.charAt(r++)]++;
            } else {
                freq[s.charAt(l++)]--;
            }
            // 这里 r - l 不加一，因为 l、r 初始化值都是0，并且是先用在加的
            res = Math.max(res, (r - l));
        }
        return res;
    }

    //public int lengthOfLongestSubstring(String s) {
    //    Set<Character> hash = new HashSet<>();
    //    // 窗口 [l,r] 内无重复元素
    //    int l = 0, r = 0;
    //    int result = 0;
    //    while (l < s.length()) {
    //        if (r < s.length() && !hash.contains(s.charAt(r))) {
    //            hash.add(s.charAt(r));
    //            r++;
    //        } else {
    //            hash.remove(s.charAt(l));
    //            l++;
    //        }
    //        // 这里 r - l 不加一，因为 l、r 初始化值都是0，并且是先用在加的
    //        result = Math.max(result, r - l);
    //    }
    //    return result;
    //}

    public static void main(String[] args) {
        System.out.println(new Solution1().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution1().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution1().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new Solution1().lengthOfLongestSubstring(" "));
    }
}