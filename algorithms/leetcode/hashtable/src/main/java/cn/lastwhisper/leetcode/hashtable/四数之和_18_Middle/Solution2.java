package cn.lastwhisper.leetcode.hashtable.四数之和_18_Middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/4sum/
     * -------------------------------------------------------------------
     *
     * -------------------------------------------------------------------
     * 优化思路：根据排序后的数据特征，在循环时可以进行跳过不必要的循环
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(n^2)
     * -------------------------------------------------------------------
     * 执行用时 : 4 ms, 在所有 java 提交中击败了99.54%的用户
     * 内存消耗 : 36.5 MB, 在所有 java 提交中击败了99.09%的用户
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
            // 最小值比target大，不管nums[i]再怎么递增都没戏
            int min1 = nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) {
                break;
            }

            // 最大值比target小，当前nums[i]肯定没戏了，找下一个nums[i]试试
            int max1 = nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1];
            if (max1 < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue; // 对j去重
                }

                // 最小值比target大，不管nums[j]再怎么递增都没戏
                int min2 = nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) {
                    break;
                }

                // 最大值比target小，当前nums[j]肯定没戏了，找下一个nums[j]试试
                int max2 = nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1];
                if (max2 < target) {
                    continue;
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

        new Solution2().fourSum(nums, target).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });
    }
}