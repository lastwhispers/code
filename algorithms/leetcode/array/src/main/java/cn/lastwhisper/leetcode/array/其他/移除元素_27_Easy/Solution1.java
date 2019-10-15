package cn.lastwhisper.leetcode.array.移除元素_27_Easy;

import java.util.Arrays;

public class Solution1 {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // 区间[0,...,pos)均为不等于val的元素
        int pos = 0;
        // nums中不等于val的值，按顺序移动到nums数组的前面。
        for (int i = 0; i < len; i++) {
            if (nums[i] != val) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        return pos;
    }

    //以下为测试代码
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println("原数组长度：" + arr.length);
        int length = new Solution1().removeElement(arr, 2);
        System.out.println("现数组长度：" + length);
        System.out.println(Arrays.toString(arr));
    }
}