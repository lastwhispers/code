package cn.cunchang.子集II_90_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cunchang
 * @date 2022/7/2 6:33 PM
 */
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        subsetsWithDup0(nums, 0);
        return result;
    }

    public void subsetsWithDup0(int[] nums, int idx) {
        result.add(new ArrayList<>(track));
        for (int i = idx; i < nums.length; i++) {
            // 跟前一个值相同，就需要剪枝
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            subsetsWithDup0(nums, i + 1);
            track.removeLast();
        }
    }

}