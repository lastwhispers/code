package cn.lastwhisper.leetcode.array.其他.卡牌分组_914_简单;

import org.junit.Assert;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：先将数组排序，对数组进行步长为2~len/2进行分组
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public boolean hasGroupsSizeX(int[] deck) {
        // 特判
        if (deck == null || deck.length <= 1) {
            return false;
        }
        if (deck.length == 2) {
            return true;
        }
        Arrays.sort(deck);
        for (int i = 2; i <= deck.length / 2; i++) {
            if (deck.length % i != 0) {//deck不能整分为i组
                continue;
            }
            boolean flag = true;
            //步长为2~len/2进行分组
            for (int len = 0, start = 0, end = i - 1; len < deck.length; end += i, start += i, len += i) {
                for (int j = start; j < end; j++) {
                    if (deck[j] != deck[j + 1]) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Assert.assertTrue(new Solution1().hasGroupsSizeX(new int[]{0, 0, 0, 0, 0, 1, 1, 1, 1, 1}));
        Assert.assertTrue(new Solution1().hasGroupsSizeX(new int[]{1, 1}));
        Assert.assertFalse(new Solution1().hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3}));
        //Assert.assertTrue(new Solution1().hasGroupsSizeX(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}));
        //Assert.assertTrue(new Solution1().hasGroupsSizeX(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
    }
}