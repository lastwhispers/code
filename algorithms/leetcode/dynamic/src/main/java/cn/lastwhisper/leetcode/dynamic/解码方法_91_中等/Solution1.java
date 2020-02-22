package cn.lastwhisper.leetcode.dynamic.解码方法_91_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/decode-ways/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int pre = 1, curr = 1;//dp[-1]= dp[0]= 1
        for (int i = 1; i < s.length(); i++) {
            int tmp = curr;
            if (s.charAt(i) == '0')
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') curr = pre;
                else return 0;
            else if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6'))
                curr = curr + pre;
            pre = tmp;
        }
        return curr;
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().numDecodings("12"));//"AB"（1 2）或者 "L"（12）
        System.out.println(new Solution1().numDecodings("226"));// "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6)
    }
}