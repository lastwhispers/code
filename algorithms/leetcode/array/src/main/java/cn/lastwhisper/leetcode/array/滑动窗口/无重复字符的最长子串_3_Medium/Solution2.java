package cn.lastwhisper.leetcode.array.无重复字符的最长子串_3_Medium;

class Solution2 {
    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/submissions/
     * 核心思想：滑动窗口，在Solution1的基础上优化
     * 时间复杂度: O(len(s))
     * 空间复杂度: O(len(charset))
     */
    public int lengthOfLongestSubstring(String s) {
        byte[] freq = new byte[256];
        int l = 0, r = 0;
        int res = 0;
        char[] chars = s.toCharArray();
        while (l < chars.length) {
            while (r < chars.length && freq[chars[r]] == 0) {
                freq[chars[r++]]++;
            }
            res = Math.max(res, (r - l));
            freq[chars[l++]]--;
        }
        return res;
    }

    //public int lengthOfLongestSubstring(String s) {
    //    byte[] freq = new byte[256];
    //    int l = 0, r = 0;
    //    int res = 0;
    //    while (l < s.length()) {
    //        while (r < s.length() && freq[s.charAt(r)] == 0) {
    //            freq[s.charAt(r++)]++;
    //        }
    //        res = Math.max(res, (r - l));
    //        freq[s.charAt(l++)]--;
    //    }
    //    return res;
    //}

    public static void main(String[] args) {
        System.out.println(new Solution2().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution2().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution2().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new Solution2().lengthOfLongestSubstring(" "));
    }
}