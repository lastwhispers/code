package cn.lastwhisper.offer.面试题40_最小的k个数;

import org.junit.Assert;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        // 特判
        if (k == 0 || arr == null || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2}, new Solution1().getLeastNumbers(new int[]{3, 2, 1}, 2));
        Assert.assertArrayEquals(new int[]{0}, new Solution1().getLeastNumbers(new int[]{0, 1, 2, 1}, 1));
    }
}