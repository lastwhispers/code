package cn.lastwhisper.leetcode.recurionbacktracking.全排列_II_47_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printLists;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形问题-递归回溯
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        // 升序，为了剪枝方便
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];
        findPermute(nums, visited, new ArrayList<>(nums.length), result);
        return result;
    }

    /**
     * @param nums 输入数字数组
     * @param visited 数字被访问过true，没有为false
     * @param permute 排列
     * @param result 返回结果
     */
    public void findPermute(int[] nums, boolean[] visited, List<Integer> permute, List<List<Integer>> result) {
        if (permute.size() == nums.length) {
            result.add(new ArrayList<>(permute));
            return;
        }
        int pre = nums[0] - 1;
        for (int i = 0; i < nums.length; i++) {
            // pre记录上一次搜索的起点，只要起点一样就跳过这个分支
            if (!visited[i] && pre != nums[i]) {
                visited[i] = true;
                permute.add(nums[i]);
                findPermute(nums, visited, permute, result);
                // backtracking
                visited[i] = false;
                permute.remove(permute.size() - 1);

                pre = nums[i];
            }
        }
    }

    public static void main(String[] args) {
        printLists(new Solution2().permuteUnique(new int[]{1, 1, 3}));
    }
}