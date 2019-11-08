package cn.lastwhisper.leetcode.array.其他.移动零_283_Easy;


import java.util.Arrays;

public class Solution3 {
    public void moveZeroes(int[] nums) {
        // k保证从[0,...,k)均为非零元素
        int k = 0;
        // temp用于交换时暂存数据
        int temp = 0;
        // 遍历到第i个元素后，保证[0,...,i]中所有非零元素，都按顺序排列在[0,...,k)中
        // 同时保证[k,...,i]中均为零元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 防止全为非零元素
                if (i != k) {
                    temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                    k++;
                } else {
                    k++;
                }
            }
        }
    }
    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        new Solution3().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}