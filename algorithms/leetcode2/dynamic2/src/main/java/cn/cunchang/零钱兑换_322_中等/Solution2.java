package cn.cunchang.零钱兑换_322_中等;

import java.util.Arrays;

/**
 * @author cunchang
 * @date 2022/6/23 5:07 PM
 */
class Solution2 {

    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().coinChange(new int[]{1, 2, 5}, 11));
//        System.out.println(new Solution().coinChange(new int[]{2}, 3));
    }
}