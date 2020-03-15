package cn.lastwhisper.leetcode.recurionbacktracking.组合总和_39_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/combination-sum/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:如何去重？
     *  A:画图观察出现重复的规律，“当前减的值比上一个减的值小”会出现重复
     *  Q:如何剪枝？
     *  A:小于0提前终止当前递归
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯——剪枝
     *  一定要看思路2，不排序输入
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        // 排序为了剪枝
        Arrays.sort(candidates);
        findCandidates(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    /**
     * dfs搜索可能的解
     * @param candidates 无重复元素的数组
     * @param residue 递归树产生的中间值，初值为target
     * @param result 解集
     * @param path 可能的解
     */
    private void findCandidates(int[] candidates, int residue, int start, List<Integer> path, List<List<Integer>> result) {
        if (residue == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        // i=start——>剪枝(去重)、residue-candidates[i]>= 0剪枝(减少运算，前提是输入排序)
        for (int i = start; i < candidates.length && residue - candidates[i] >= 0; i++) {
            path.add(candidates[i]);
            findCandidates(candidates, residue - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        //int[] candidates = {2, 3, 6, 7};
        //int target = 7;
        //int[] candidates = {2, 3, 5};
        int[] candidates = {5, 3, 2};
        int target = 8;
        printLists(new Solution1().combinationSum(candidates, target));
    }
}