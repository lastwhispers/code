package cn.lastwhisper.leetcode.array.移动零_283_简单;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/min-stack/
     * 编号：283
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public void moveZeroes(int[] nums) {
        // 缓存非0元素
        List<Integer> nonZeroElements = new ArrayList<>();
        // 找出非0元素并将它缓存进nonZeroElements
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroElements.add(nums[i]);
            }
        }
        // 将非0元素放入原数组中
        for (int i = 0; i < nonZeroElements.size(); i++) {
            nums[i] = nonZeroElements.get(i);
        }
        // 将原数组剩余的位置放置为0
        for (int i = nonZeroElements.size(); i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        new Solution1().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}