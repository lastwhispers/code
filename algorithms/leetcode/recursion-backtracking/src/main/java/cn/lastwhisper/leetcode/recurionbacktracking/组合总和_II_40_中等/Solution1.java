package cn.lastwhisper.leetcode.recurionbacktracking.组合总和_II_40_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/combination-sum-ii/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯——剪枝
     *  这题一定要排序，排序之后方便去重。39题可以不排序，还记得39题不排序如何去重的吗？
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        // 排序为了去重
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
        // 升序输入可以在递归进入前退出，如果没升序，必须在这里退出
        //if (residue < 0) {
        //    return;
        //}
        // residue-candidates[i]>=0 剪枝(减少运算)
        for (int i = start; i < candidates.length && residue - candidates[i] >= 0; i++) {
            // 剪枝(去重)，重复的元素一定不是排好序以后的第 1 个元素和相同元素的第 1 个元素
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            // 因为元素不可以重复使用，这里递归传递下去的是 i + 1 而不是 i
            findCandidates(candidates, residue - candidates[i], i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        //int[] candidates = {2,5,2,1,2};
        //int target = 5;
        printLists(new Solution1().combinationSum2(candidates, target));
    }
}