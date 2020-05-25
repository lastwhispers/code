package cn.lastwhisper.leetcode.array.移动零_283_简单;


import java.util.Arrays;

public class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/min-stack/
     * 编号：283
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：快慢指针
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void moveZeroes(int[] nums) {
        // k保证从[0,...,k)均为非零元素，k 表示0的索引
        int k = 0;
        // temp用于交换时暂存数据
        int temp;
        // 遍历到第i个元素后，保证[0,...,i]中所有非零元素，都按顺序排列在[0,...,k)中
        // 同时保证[k,...,i]中均为零元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) { // 防止数组本身就是“有序”，这里的有序指
                    temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                }
                k++;
            }
        }
    }

    //以下为测试代码
    public static void main(String[] args) {
        //int[] arr = {0, 1, 0, 3, 12};
        int[] arr = {0, 0, 0, 0, 0};
        new Solution3().moveZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }
}