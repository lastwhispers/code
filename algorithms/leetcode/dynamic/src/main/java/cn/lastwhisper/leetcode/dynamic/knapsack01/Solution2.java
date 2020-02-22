package cn.lastwhisper.leetcode.dynamic.knapsack01;

/**
 * 01背包
 * @author lastwhisper
 * @date 2020/2/20
 */
public class Solution2 {


    private int bestValue(int[] w, int[] v, int index, int capacity, int[][] memo) {
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        if (memo[index][capacity] != 0) {
            return memo[index][capacity];
        }
        // 尝试[0,index-1]的商品
        int res = bestValue(w, v, index - 1, capacity, memo);
        if (capacity >= w[index]) {
            // 尝试index的商品和[0,index-1]时capacity等于减去w[index]的商品
            res = Math.max(res,v[index] +  bestValue(w, v, index - 1, capacity - w[index], memo));
        }
        memo[index][capacity] = res;
        return res;
    }

    /**
     * 01背包
     *  递归+备忘录
     *
     * @param w 每个商品对应的重量
     * @param v 每个商品对应的价值
     * @param capacity 背包容量
     * @return int 最大价值
     */
    public int knapsack01(int[] w, int[] v, int capacity) {
        int n = w.length;
        int[][] memo = new int[n][capacity + 1];
        return bestValue(w, v,n-1, capacity,  memo);
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
        System.out.println(new Solution2().knapsack01(w, v, capacity));
    }
}
