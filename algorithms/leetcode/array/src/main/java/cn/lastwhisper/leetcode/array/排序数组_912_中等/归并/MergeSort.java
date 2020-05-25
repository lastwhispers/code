package cn.lastwhisper.leetcode.array.排序数组_912_中等.归并;

import org.junit.Assert;

class MergeSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  归并排序——稳定
     *
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
        return nums;
    }

    /**
     * 归并排序
     * 分
     *
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左分
            mergeSort(arr, left, mid, temp);
            // 向右分
            mergeSort(arr, mid + 1, right, temp);
            // 治
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 治
     * @param arr 排序的原始数组 arr[left...mid] arr[mid+1...right]
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right 右边索引
     * @param temp 中转数组
     */
    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //左边有序序列的初始索引
        int j = mid + 1; // 右边有序序列的初始化序索引
        int t = 0; // 指向temp数组的当前位置

        // 1）、将左右两边（有序）的数据按照规则填充到temp数组，任意一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                // 左小于右，将左数组第i个元素copy到temp数组的第t个位置
                temp[t++] = arr[i++];
            } else {
                // 右小于或等于左，将右数组第j个元素copy到temp数组的第t个位置
                temp[t++] = arr[j++];
            }
        }

        // 2）、把有剩余数据的一边的数据依次填充到temp
        while (i <= mid) {
            // 说明 左数组还有剩余元素
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            // 说明 右数组还有剩余元素
            temp[t++] = arr[j++];
        }

        // 3）、将temp数组的数据copy到arr，[left,right]是每次copy的范围
        for (int k = left, l = 0; k <= right; k++, l++) {
            arr[k] = temp[l];
        }

    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new MergeSort().sortArray(new int[]{5, 2, 3, 1}));
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 5}, new MergeSort().sortArray(new int[]{5, 1, 1, 2, 0, 0}));
    }
}