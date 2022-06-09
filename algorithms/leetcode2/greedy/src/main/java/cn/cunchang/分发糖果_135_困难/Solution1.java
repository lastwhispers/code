package cn.cunchang.分发糖果_135_困难;

import org.junit.Assert;

import java.util.Arrays;

public class Solution1 {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * 从前往后贪一次，从后往前再贪一次，不过第二次要判断大小，只贪最大的哪个
     * <p>
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int candy(int[] ratings) {
        if (ratings.length < 2) {
            return ratings.length;
        }
        int[] resArr = new int[ratings.length];
        Arrays.fill(resArr, 1);
        // 先从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的糖果数加 1；
        for (int i = 1; i < ratings.length; i++) {// i = 1而不是i = 0
            if (ratings[i] > ratings[i - 1]) {
                resArr[i] = resArr[i - 1] + 1;
            }
        }
        // 再从右往左遍历一遍，如果左边孩子的评分比右边的高，且左边孩子当前的糖果数不大于右边孩子的糖果数，
        // 则左边孩子的糖果数更新为右边孩子的糖果数加 1。
        for (int i = ratings.length - 1; i > 0; i--) {// i > 0而不是i >= 0
            if (ratings[i] < ratings[i - 1]) {
                resArr[i - 1] = Math.max(resArr[i - 1], resArr[i] + 1);
            }
        }

        int count = 0;
        for (int res : resArr) {
            count += res;
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] g = new int[]{1, 2, 87, 87, 87, 2, 1};
        int[] g = new int[]{1, 0, 2};
        Assert.assertEquals(5, new Solution1().candy(g));
    }

}