package cn.cunchang.全排列II_47_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cunchang
 * @date 2022/7/2 4:02 PM
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> subResult = new LinkedList<>();
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] visited = new boolean[nums.length];
        // 保证有序
        Arrays.sort(nums);
        permute0(nums, subResult, visited);
        return result;
    }

    private void permute0(int[] nums, LinkedList<Integer> subResult, boolean[] visited) {
        // 回溯结束，记录结果
        if (nums.length == subResult.size()) {
            result.add(new ArrayList<>(subResult));
        }
        // 记录上一次选择的值
        int prev = Integer.MIN_VALUE;
        // 遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 选择值相同，则不选
            if (prev == nums[i]) {
                continue;
            }
            prev = nums[i];
            // 选择值
            subResult.add(nums[i]);
            visited[i] = true;
            permute0(nums, subResult, visited);
            // 撤销选择
            subResult.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().permuteUnique(new int[]{1, 2, 1}));
    }

}