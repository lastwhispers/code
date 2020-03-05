package cn.lastwhisper.offer.面试题14_I_剪绳子_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *  1、状态定义：dp[n]表示长度为n的绳子剪成若干段后得到的乘积最大值
     *  2、状态分析
     *      初始化：
     *          n>1 dp[2]=1、dp[3]=2
     *      常规：
     *          dp[n]=max(i*(n-i),i*dp[n-i])
     *  3、转换方程：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int cuttingRope(int n) {
        // 2=>1*1=1
        // 3=>1*2=2
        int[] memo = new int[n + 1];
        memo[1] = 1;
        // 外循环将2~n的最大乘积推算出来
        for (int i = 2; i <= n; i++) {
            // 内循环分割i(1~i-1)，推算i的最大乘积
            for (int j = 1; j < i; j++) {
                //
                memo[i] = Math.max(memo[i], Math.max(j * (i - j), j * memo[i - j]));
            }
        }
        return memo[n];
    }

    public static void main(String[] args) {
        System.err.println(new Solution1().cuttingRope(10));
    }
}