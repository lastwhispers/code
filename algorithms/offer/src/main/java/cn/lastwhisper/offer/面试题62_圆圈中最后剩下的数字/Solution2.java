package cn.lastwhisper.offer.面试题62_圆圈中最后剩下的数字;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-by-lee/
     *  将上述问题建模为函数 f(n, m)，该函数的返回值为最终留下的元素的序号
     *     长度为n的序列会先删除第m%n个元素，然后剩下一个长度为n-1的序列，递归地求解f(n-1,m)，设x=f(n-1,m)
     *     f(n - 1, m) = (m % n + x) % n = (m + x) % n
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int lastRemaining(int n, int m) {
        //int ans = 0;
        //// 最后一轮剩下2个人，所以从2开始反推
        //for (int i = 2; i <= n; i++) {
        //    ans = (ans + m) % i;
        //}
        //return ans;
        return f(n, m);
    }

    private int f(int n, int m) {
        // 当序列长度为 1 时，一定会留下唯一的那个元素，它的编号为 0
        if (n == 1){
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

    public static void main(String[] args) {
        Assert.assertEquals(3, new Solution2().lastRemaining(5, 3));
    }
}