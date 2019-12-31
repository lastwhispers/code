package cn.lastwhisper.leetcode.hashtable.三数之和_15_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/3sum/
     * -------------------------------------------------------------------
     *
     * -------------------------------------------------------------------
     * 优化思路：根据排序后的数据规律，剔除掉不必要的循环
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     * -------------------------------------------------------------------
     * 执行用时 :33 ms, 在所有 java 提交中击败了92.67%的用户
     * 内存消耗 :48.1 MB, 在所有 java 提交中击败了92.84%的用户
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 存储结果
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 3) return resultList;
        // 数组排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) { // i < nums.length - 2

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 对i去重
            }

            // 最小值比0大，不管nums[i]再怎么递增都没戏
            int min1 = nums[i] + nums[i + 1] + nums[i + 2];
            if (min1 > 0) {
                break;
            }

            // 最大值比0小，当前nums[i]肯定没戏了，找下一个nums[i]试试
            int max1 = nums[i] + nums[nums.length - 2] + nums[nums.length - 1];
            if (max1 < 0) {
                continue;
            }

            int l = i + 1, r = nums.length - 1;
            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    resultList.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;   // 对l去重
                    while (l < r && nums[r] == nums[r - 1]) r--;   // 对r去重
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }

            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        //new Solution2().threeSum(new int[]{-1, 0, 1, 2, -1, -4}).forEach((list) -> {
        //    System.out.println();
        //    list.forEach(System.out::print);
        //});

        new Solution3().threeSum(new int[]{0, 0, 0, 0}).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });
    }
}