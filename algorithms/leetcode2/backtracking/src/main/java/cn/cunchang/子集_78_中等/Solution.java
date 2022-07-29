package cn.cunchang.子集_78_中等;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cunchang
 * @date 2022/7/2 5:03 PM
 */
class Solution {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return result;
    }

    public void backtrack(int[] nums, int idx) {
        result.add(new LinkedList<>(track));
        // 选择列表中选择
        for (int i = idx; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            backtrack(nums, i + 1);
            // 撤销选择
            track.removeLast();
        }
    }

}