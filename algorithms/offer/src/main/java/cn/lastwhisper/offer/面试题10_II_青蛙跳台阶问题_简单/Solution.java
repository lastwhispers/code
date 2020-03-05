package cn.lastwhisper.offer.面试题10_II_青蛙跳台阶问题_简单;

class Solution {
    // cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单.Solution5
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n < 2) return n;
        int item1 = 1;
        int item2 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (item1 + item2) % 1000000007;
            item1 = item2;
            item2 = sum;
        }
        return sum;
    }
}