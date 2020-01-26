package cn.lastwhisper.leetcode.stackqueue.完全平方数_279_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/perfect-squares/
     * -------------------------------------------------------------------
     * 思考：无权图的BFS、迪杰斯特拉
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 1; i <= n; i++){
            for(int j = 1; j*j <= i; j++){
                if(i >= j*j){
                    dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().numSquares(12));
    }
}