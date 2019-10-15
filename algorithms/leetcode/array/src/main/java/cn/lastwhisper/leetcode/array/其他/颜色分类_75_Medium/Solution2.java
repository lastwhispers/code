package cn.lastwhisper.leetcode.array.颜色分类_75_Medium;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class Solution2 {

    /**
     * 题目：https://leetcode-cn.com/problems/sort-colors/
     *  三路快速排序的思想，对整个数组只遍历了一遍
     *  时间复杂度: O(n)
     *  空间复杂度: O(1)
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int zero = 0, two = nums.length;
        for (int i = 0; i < two; ) { // 小于two与i不加相对应
            int num = nums[i];
            if (num == 1) {
                i++;
            } else if (num == 0) {
                swap(nums, zero++, i++);
            } else if (num == 2) {
                swap(nums, --two, i);// i不加
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        new Solution2().sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }

}
