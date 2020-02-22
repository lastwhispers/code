package cn.lastwhisper.leetcode.dynamic.knapsack01;

/**
 * 01背包
 * @author lastwhisper
 * @date 2020/2/20
 */
public class Solution4 {

    /**
     * 01背包 动态规划
     *
     * 时间复杂度：O(n*c)
     * 空间复杂度：O(2*c)
     *
     * F(n,c)考虑将n个物品放进容量为c的背包，使得价值最大
     *  F(i,c) = max(F(i-1,c),v[i]+F(i-1,c-w[i]))
     *
     *  空间优化：
     *      第i行元素只依赖于第i-1行元素。理论上，只需要保持两行元素。
     *      空间复杂度：O( 2 * C ) = O(C)
     *
     * @param w 每个商品对应的重量
     * @param v 每个商品对应的价值
     * @param c 背包容量
     * @return int 最大价值
     */
    public int knapsack01(int[] w, int[] v, int c) {
        int n = w.length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[2][c + 1];

        // 初始化第一行
        for (int i = 0; i <= c; i++) {
            dp[0][i] = i >= w[0] ? v[0] : 0;
        }

        // 外循环，当前可选商品
        for (int i = 1; i < n; i++) {
            // 内循环，当前可用背包大小
            for (int j = 0; j <= c; j++) {
                // 不考虑当前商品
                dp[i % 2][j] = dp[(i - 1) % 2][j];
                // 考虑当前商品+剩余容量时剩下商品
                if (j >= w[i % 2]) {
                    dp[i % 2][j] = Math.max(dp[i % 2][j], v[i% 2] + dp[(i - 1) % 2][j - w[i % 2]]);
                }
            }
        }

        return dp[(n - 1) % 2][c];
    }

    /**
     * 容量为：5
     * 三个物品：
     *      w 1  2  3
     *      v 6 10 12
     *
     * 最大价值22
     */
    public static void main(String[] args) {
        int[] w = new int[]{1, 2, 3};
        int[] v = new int[]{6, 10, 12};
        int capacity = 5;
        System.out.println(new Solution4().knapsack01(w, v, capacity));
    }
}
