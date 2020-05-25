package cn.lastwhisper.leetcode.array.卡牌分组_914_简单;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：统计每个数出现的次数，次数的最大公约数大于1即可
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean hasGroupsSizeX(int[] deck) {
        // 特判
        if (deck == null || deck.length <= 1) {
            return false;
        }
        int[] freq = new int[10000];
        for (int num : deck) {
            freq[num]++;
        }

        int x = freq[deck[0]];
        for (int num : freq) {
            // 1个数不能分组
            if (num == 1) {
                return false;
            }
            if (num > 1) {
                x = gcd(x, num);
                // 最小公倍数是1说明也不能按同样的值分组
                if (x == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Assert.assertTrue(new Solution2().hasGroupsSizeX(new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1}));
        Assert.assertTrue(new Solution2().hasGroupsSizeX(new int[]{1, 1}));
        Assert.assertFalse(new Solution2().hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
        //Assert.assertTrue(new Solution2().hasGroupsSizeX(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}));
        //Assert.assertTrue(new Solution2().hasGroupsSizeX(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
    }
}