package cn.lastwhisper.leetcode.other.分糖果II_1103_简单;

import java.util.Arrays;

class Solution {
    /**
     * 题目地址：https://leetcode-cn.com/problems/distribute-candies-to-people/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(num_people)
     */
    public int[] distributeCandies(int candies, int num_people) {

        int[] nums = new int[num_people];

        // 应该给的糖果
        int should = 1;
        while (candies > 0) {
            for (int i = 0; i < num_people; i++) {
                // 当前剩余糖果大于应该给的糖果
                if (candies > should) {
                    candies -= should;
                    nums[i] += should;
                } else {
                    nums[i] += candies;
                    candies -= candies;
                    break;
                }
                should++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        //int candies = 7, num_people = 4;
        int candies = 10, num_people = 3;
        System.out.println(Arrays.toString(new Solution().distributeCandies(candies, num_people)));
    }
}