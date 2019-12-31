package cn.lastwhisper.leetcode.hashtable.最接近的三数之和_16_Middle;

import java.util.Arrays;

class Solution1 {

    /**
     * 题目地址：https://leetcode-cn.com/problems/3sum-closest/
     * -------------------------------------------------------------------
     * 思考：
     *  Q:什么叫做接近？
     *  A: abs((n1+n2+n3)-target)>abs((n2+n3+n4)-target)
     *      n2+n3+n4比n1+n2+n3接近target
     * 数据特征：
     *     输入：数组、无序、所有整数
     *     输出：整数
     * -------------------------------------------------------------------
     * 思路：在三数之和的基础上，理解什么叫做“接近”即可
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)：
     * -------------------------------------------------------------------
     * 执行用时 :7 ms, 在所有 java 提交中击败了58.60%的用户
     * 内存消耗 :36.6 MB, 在所有 java 提交中击败了84.07%的用户
     */
    public int threeSumClosest(int[] nums, int target) {

        int closest = nums[0] + nums[1] + nums[2];
        // 数组排序2
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 对i去重
            }

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(target - sum) < Math.abs(target - closest)) {
                    closest = sum;
                }

                if (sum < target) {
                    l++;
                } else if (sum > target) {
                    r--;
                } else {
                    return closest;
                }

            }
        }

        return closest;
    }

    public static void main(String[] args) {
        //example
        int[] nums = {-1, 2, 1, -4};
        int target = 1;

        System.out.println(new Solution1().threeSumClosest(nums, target));
    }
}