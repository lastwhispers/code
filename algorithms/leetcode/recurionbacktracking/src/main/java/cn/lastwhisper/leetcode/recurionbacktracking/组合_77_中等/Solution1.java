package cn.lastwhisper.leetcode.recurionbacktracking.组合_77_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.collection2String;
import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution1 {
    static int count = 0;

    /**
     * 题目地址：https://leetcode-cn.com/problems/combinations/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:集合初始化大小
     *  A:result集合承载着所有组合的情况，初始化大小=C(n,k)
     *      combine集合承载每个节点的情况，最大不会超过k
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
        for (int i = start; i <= n; i++) {
            combine.add(i);
            findCombinations(i + 1, n, k, combine, result);
            combine.remove(combine.size() - 1);
        }
    }

    //private void dubugFindCombinations(int start, int n, int k, List<Integer> combine, List<List<Integer>> result) {
    //    count++;
    //    if (combine.size() == k) {
    //        result.add(new ArrayList<>(combine));
    //        System.out.println("递归终止——第" + count + "层递归——combine=" + collection2String(combine));
    //        return;
    //    }
    //    for (int i = start; i <= n; i++) {
    //        combine.add(i);
    //        System.out.println("递归——第" + count + "层递归,combine=" + collection2String(combine));
    //        dubugFindCombinations(i + 1, n, k, combine, result);
    //        count--;
    //        combine.remove(combine.size() - 1);
    //        System.out.println("回溯————第" + count + "层递归,combine=" + collection2String(combine));
    //    }
    //    System.out.println("递归终止——第" + count + "层递归——combine=" + collection2String(combine));
    //}

    //初始化容量
    private int initCapacity(int n, int k) {
        return (int) (factorial(n) / (factorial(n - k) * factorial(k)));
    }

    // 阶乘
    private long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        //printLists(new Solution1().combine(4, 2));
        printLists(new Solution1().combine(5, 3));
        //System.out.println(new Solution1().initCapacity(20, 16));
    }
}