package cn.lastwhisper.leetcode.recurionbacktracking.组合总和_39_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtil.printLists;

class Solution2 {
    static int count = 0;

    /**
     * 题目地址：https://leetcode-cn.com/problems/combination-sum/
     * -------------------------------------------------------------------
     * 思考：
     * Q:如何去重？
     * A:画图观察出现重复的规律，“当前减的值比上一个减的值小”会出现重复
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯
     *    不排序输入
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
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
        if (residue < 0) {
            return;
        }
        // i=start——>剪枝(去重)
        for (int i = start; i < candidates.length; i++) {
            // 不能再这里终止递归，因为组合数字可能无序，后面的数字可能和为target
            //if(residue - candidates[i]<0){
            //    return;
            //}
            path.add(candidates[i]);
            findCandidates(candidates, residue - candidates[i], i, path, result);
            path.remove(path.size() - 1);
        }
    }

    //private void dubugFindCandidates(int[] candidates, int residue, int start, List<Integer> path, List<List<Integer>> result) {
    //    count++;
    //    if (residue == 0) {
    //        result.add(new ArrayList<>(path));
    //        System.out.println("递归终止——第" + count + "层递归——residue= " + residue + "——path=" + listConvert(path));
    //        return;
    //    }
    //    if (residue < 0) {
    //        System.out.println("递归终止——第" + count + "层递归——residue= " + residue + "——path=" + listConvert(path));
    //        return;
    //    }
    //    for (int i = start; i < candidates.length; i++) {
    //        // 不能再这里终止递归，因为组合数字可能无序，后面的数字可能和为target
    //        //if(residue - candidates[i]<0){
    //        //    return;
    //        //}
    //        path.add(candidates[i]);
    //        System.out.println("递归——第" + count + "层递归——residue= " + residue + "——path=" + listConvert(path));
    //        findCandidates(candidates, residue - candidates[i], i, path, result);
    //        count--;
    //        path.remove(path.size() - 1);
    //        System.out.println("回溯————第" + count + "层递归——residue= " + residue + "——path=" + listConvert(path));
    //    }
    //    System.out.println("递归终止——第" + count + "层递归——residue= " + residue + "——path=" + listConvert(path));
    //}

    public static void main(String[] args) {
        int[] candidates = {2, 6, 3, 7};
        int target = 7;
        //int[] candidates = {2, 3, 5};
        //int[] candidates = {5, 3, 2};
        //int target = 8;
        printLists(new Solution2().combinationSum(candidates, target));
    }
}