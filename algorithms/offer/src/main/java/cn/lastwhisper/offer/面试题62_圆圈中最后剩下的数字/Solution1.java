package cn.lastwhisper.offer.面试题62_圆圈中最后剩下的数字;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *      通过取模模拟环状
     * -------------------------------------------------------------------
     * 时间复杂度：O(mn)
     * 空间复杂度：O(n)
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            // 模拟环状，-1是因为移除一个数
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }

        return list.get(0);
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, new Solution1().lastRemaining(5, 3));
    }
}