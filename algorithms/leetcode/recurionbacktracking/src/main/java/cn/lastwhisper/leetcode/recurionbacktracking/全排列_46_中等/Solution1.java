package cn.lastwhisper.leetcode.recurionbacktracking.全排列_46_中等;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.collection2String;
import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution1 {
    static Integer count = 0;

    /**
     * 题目地址：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:为什么需要回溯？
     *  A:在树形问题中，每一个节点保存了上一个结点的值，由于这里使用一个集合存储结点值，
     *   为了复用这个集合，必须回溯，重置集合的状态。
     *  Q:可以不回溯吗？
     *  A:可以，每个结点存储上一个结点的值即可，不过这样会产生大量的临时变量。
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfsPermute(nums, visited, new ArrayList<>(), result);
        return result;
    }

    /**
     * @param nums 输入数字数组
     * @param visited 数字被访问过true，没有为false
     * @param permute 排列
     * @param result 返回结果
     */
    private void dfsPermute(int[] nums, boolean[] visited, List<Integer> permute, List<List<Integer>> result) {
        count++;
        if (permute.size() == nums.length) {
            // 每次必须new，因为这个permute集合需要重复使用
            result.add(new ArrayList<>(permute));
            System.out.println("递归终止——第" + count + "层递归——permute=" + collection2String(permute));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 使用visited数组(O(1))查询是否访问过，而不使用List.contains(O(n))。
            if (!visited[i]) {
                visited[i] = true;
                permute.add(nums[i]);
                System.out.println("递归——第" + count + "层递归——visited[" + i + "]=" + visited[i] + ",permute=" + collection2String(permute));
                dfsPermute(nums, visited, permute, result);
                count--;
                // backtracking
                visited[i] = false;
                permute.remove(permute.size() - 1);
                System.out.println("回溯————第" + count + "层递归——visited[" + i + "]=" + visited[i] + ",permute=" + collection2String(permute));
            }
        }
        System.out.println("递归终止——第" + count + "层递归——permute=" + collection2String(permute));
    }

    public static void main(String[] args) {
        printLists(new Solution1().permute(new int[]{1, 2, 3}));
    }
}