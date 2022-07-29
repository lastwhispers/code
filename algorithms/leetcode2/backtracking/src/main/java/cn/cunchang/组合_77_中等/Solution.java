package cn.cunchang.组合_77_中等;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cunchang
 * @date 2022/7/2 4:59 PM
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        backtrack(nums, 0, k);
        return result;
    }

    public void backtrack(int[] nums, int idx, int k) {
        if (track.size() == k) {
            result.add(new LinkedList<>(track));
            return;
        }
        // 选择列表中选择
        for (int i = idx; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            backtrack(nums, i + 1, k);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combine(4, 2));
    }

}