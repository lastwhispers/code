package cn.lastwhisper.leetcode.hashtable.四数之和_18_Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/3sum/
     * -------------------------------------------------------------------
     * 思考：如何去重？
     * 数据特征：
     *     输入：数组、无序、所有整数
     *     输出：二维数组
     * -------------------------------------------------------------------
     * 思路：三数之和添加一层循环
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 存储结果
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 4) return resultList;
        // 数组排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 对i去重
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 对j去重
                }
                int l = j + 1, r = nums.length - 1;
                while (l < r) {

                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum == target) {
                        resultList.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 对l去重
                        while (l < r && nums[r] == nums[r - 1]) r--;   // 对r去重
                        l++;
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        r--;
                    }

                }

            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 0, -1, 0, -2, 2};
        //int target = 0;

        // error example
        //int[] nums = {-3, -1, 0, 2, 4, 5};
        //int target = 2;

        int[] nums = {-3, -2, -1, 0, 0, 1, 2, 3};
        int target = 0;

        new Solution1().fourSum(nums, target).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });
    }
}