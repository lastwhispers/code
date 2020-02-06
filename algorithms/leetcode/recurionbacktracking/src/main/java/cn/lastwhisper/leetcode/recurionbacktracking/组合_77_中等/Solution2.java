package cn.lastwhisper.leetcode.recurionbacktracking.组合_77_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/combinations/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:如何剪枝
     *  A:画图推导
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n < k) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>(initCapacity(n, k));
        findCombinations(1, n, k, new ArrayList<>(k), result);
        return result;
    }

    //初始化容量
    private int initCapacity(int n, int k) {
        return (int) (factorial(n) / (factorial(n - k) * factorial(k)));
    }

    // 阶乘
    private long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     *
     * @param start 返回start...n中所有可能的k个数的组合
     * @param n C(n,k)
     * @param k C(n,k)
     * @param combine 保存当前结点产生的组合
     * @param result 返回结果
     */
    private void findCombinations(int start, int n, int k, List<Integer> combine, List<List<Integer>> result) {
        if (combine.size() == k) {
            result.add(new ArrayList<>(combine));
            return;
        }
        /*
         * n=5，k=3时
         *  combine.size()=1时，接下来要取2个数，i最大取4，最后被选择的是:[4,5]；
         *  combine.size()=2时，接下来要取1个数，i最大取5，最后被选择的是:[5]；
         * max(i)+接下选择元素个数-1=n、接下选择元素个数=k-combine.size()===》max(i)=n-(k-combine.size())+1
         *  4=5-3+1+1
         *  5=5-3+2+1
         *
         *  还有k - c.size()个空位, 所以, [i...n] 中至少要有 k - c.size() 个元素
         *  i最多为 n - (k - c.size()) + 1
         */
        for (int i = start; i <= n - (k - combine.size()) + 1; i++) {
            combine.add(i);
            findCombinations(i + 1, n, k, combine, result);
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {
        printLists(new Solution2().combine(4, 2));
        //System.out.println(new Solution1().initCapacity(20, 16));
    }
}