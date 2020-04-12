package cn.lastwhisper.leetcode.hashtable.长回文串_409_简单;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/longest-palindrome/
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     * -------------------------------------------------------------------
     */
    public int longestPalindrome(String s) {
        int[] freq = new int[58];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'A']++;
        }
        int ans = 0;
        for (int num : freq) {
            // 偶数直接可以组成回文串，奇数取最大偶数组成回文串
            //不等价 if (num != 0) ans += num;return ans ;
            //简写 if (num != 0) ans += num - (num & 1);
            if (num != 0) {
                if (num % 2 == 0) {
                    ans += num;
                } else {
                    ans += num - 1;//大于1的奇数串只取偶数个，return补偿
                }
            }
        }
        return ans < s.length() ? ans + 1 : ans;
    }

    public static void main(String[] args) {
        //Assert.assertEquals(7, new Solution1().longestPalindrome("abccccdd"));
        Assert.assertEquals(3, new Solution1().longestPalindrome("ccc"));
    }

}