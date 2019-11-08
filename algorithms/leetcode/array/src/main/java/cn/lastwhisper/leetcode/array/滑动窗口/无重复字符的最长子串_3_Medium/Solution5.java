package cn.lastwhisper.leetcode.array.滑动窗口.无重复字符的最长子串_3_Medium;

// 3. Longest Substring Without Repeating Characters
// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
//
// 滑动窗口
// 时间复杂度: O(len(s))
// 空间复杂度: O(len(charset))
public class Solution5 {
    public int lengthOfLongestSubstring(String s) {

        int[] freq = new int[256];

        int l = 0, r = -1; //滑动窗口为s[l...r]
        int res = 0;

        // 在这里, 循环中止的条件可以是 r + 1 < s.length(), 想想看为什么?
        // 感谢课程QQ群 @千千 指出 :)
        while( r + 1 < s.length() ){

            if( r + 1 < s.length() && freq[s.charAt(r+1)] == 0 )
                freq[s.charAt(++r)] ++;
            else    //freq[s[r+1]] == 1
                freq[s.charAt(l++)] --;

            res = Math.max(res, r-l+1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((new Solution5()).lengthOfLongestSubstring( "abcabcbb" ));
        System.out.println((new Solution5()).lengthOfLongestSubstring( "bbbbb" ));
        System.out.println((new Solution5()).lengthOfLongestSubstring( "pwwkew" ));
        System.out.println((new Solution5()).lengthOfLongestSubstring( "" ));
    }
}
