package cn.cunchang.零钱兑换_322_中等;

import java.util.HashMap;

/**
 * @author cunchang
 * @date 2022/6/23 5:07 PM
 */
class Solution {

    public int coinChange(int[] coins, int amount) {
        HashMap<Integer, Integer> meno = new HashMap<>();
        return coinChange0(coins, amount, meno);
    }

    public int coinChange0(int[] coins, int amount, HashMap<Integer, Integer> meno) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (meno.get(amount) != null) {
            return meno.get(amount);
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subRes = coinChange0(coins, amount - coin, meno);
            // 子问题无解
            if (subRes == -1) {
                continue;
            }
            // 找子问题最小解
            res = Math.min(res, subRes + 1);
        }

        res = (res == Integer.MAX_VALUE) ? -1 : res;
        // 对amount的结果进行缓存
        meno.put(amount, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChange(new int[]{1, 2, 5}, 11));
//        System.out.println(new Solution().coinChange(new int[]{2}, 3));
    }
}