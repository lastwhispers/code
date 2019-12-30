package cn.lastwhisper.leetcode.hashtable.三数之和_15_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/3sum/
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：数组、无序、所有整数
     *     输出：二维数组
     * -------------------------------------------------------------------
     * 思路：三三组合改进，排序+双索引，并对特殊情况进行剔除
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 存储结果
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 3) return resultList;
        // 数组排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break; //此时说明数组后面都是正值，三个数相加不可能为0
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 对i去重
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

        new Solution2().threeSum(new int[]{0,0,0,0}).forEach((list) -> {
            System.out.println();
            list.forEach(System.out::print);
        });
    }
}