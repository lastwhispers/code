package cn.lastwhisper.leetcode.array.滑动窗口.无重复字符的最长子串_3_Medium;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/submissions/
     * 核心思想：滑动窗口
     * 时间复杂度: O(len(s))
     * 空间复杂度: O(len(charset))
     */
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        int l = 0, r = 0;
        int res = 0;
        char[] chars = s.toCharArray();
        while (l < chars.length) {
            if (r < chars.length  && freq[chars[r]] == 0) {
                freq[chars[r++]]++;
            } else {
                freq[chars[l++]]--;
            }
            res = Math.max(res, (r - l));
        }
        return res;
    }

    //public int lengthOfLongestSubstring(String s) {
    //    int[] freq = new int[256];
    //    int l = 0, r = 0;
    //    int res = 0;
    //    while (l < s.length()) {
    //        if (r <  s.length()  && freq[s.charAt(r)] == 0) {
    //            freq[s.charAt(r++)]++;
    //        } else {
    //            freq[s.charAt(l++)]--;
    //        }
    //        res = Math.max(res, (r - l));
    //    }
    //    return res;
    //}

    public static void main(String[] args) {
        System.out.println(new Solution1().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution1().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution1().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new Solution1().lengthOfLongestSubstring(" "));
    }
}