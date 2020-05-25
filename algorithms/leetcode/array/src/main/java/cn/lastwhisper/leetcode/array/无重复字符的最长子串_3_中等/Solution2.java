package cn.lastwhisper.leetcode.array.无重复字符的最长子串_3_中等;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     * 编号：3
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：双指针（滑动窗口）
     *  优化 Solution1
     * -------------------------------------------------------------------
     * 时间复杂度：O(n) // 字符串的长度
     * 空间复杂度：O(1) // 窗口长度
     */
    public int lengthOfLongestSubstring(String s) {
        byte[] freq = new byte[256];
        int l = 0, r = 0;
        int res = 0;
        while (l < s.length()) {
            while (r < s.length() && freq[s.charAt(r)] == 0) {
                freq[s.charAt(r++)]++;
            }
            res = Math.max(res, (r - l));
            freq[s.charAt(l++)]--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new Solution2().lengthOfLongestSubstring("bbbbb"));
        System.out.println(new Solution2().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new Solution2().lengthOfLongestSubstring(" "));
    }
}