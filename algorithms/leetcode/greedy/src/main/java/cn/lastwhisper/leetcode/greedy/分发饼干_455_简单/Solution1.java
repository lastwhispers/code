package cn.lastwhisper.leetcode.greedy.分发饼干_455_简单;

import java.util.Arrays;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/assign-cookies/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：贪心算法
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        // 排序，从小到大
        Arrays.sort(g);
        Arrays.sort(s);
        for (int i = g.length - 1, j =  s.length - 1; i >= 0 && j >= 0; i--) {
            // 最大的饼干，满足最贪心的孩子
            if (s[j] >= g[i]) {
                count++;
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //int[] g = {1,2},s={1,2,3};
        int[] g = {10, 9, 8, 7}, s = {5, 6, 7, 8};
        System.out.println(new Solution1().findContentChildren(g, s));
    }
}