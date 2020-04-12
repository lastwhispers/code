package cn.lastwhisper.leetcode.array.排序.排序数组_912_中等.交换;

import org.junit.Assert;

class QuickSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  快速排序——不稳定
     *  https://zh.wikipedia.org/wiki/%E5%BF%AB%E9%80%9F%E6%8E%92%E5%BA%8F
     *
     *  快速排序（Quick Sort）是由冒泡排序改进而得的。在冒泡排序过程中，只对相邻的两个记录进行比较，因此每次交换两个相邻记录时只能消除一个逆序。
     *  如果能通过两个（不相邻）记录的一-次交换，消除多个逆序，则会大大加快排序的速度。快速排序方法中的一次交换可能消除多个逆序。
     *
     *  基准值可以选择第一个值、中间值、最后一个值
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(nlogn)
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left < right) {// 长度大于1
            int pivotLoc = partition(nums, left, right);//中轴下标
            quickSort(nums, left, pivotLoc - 1);
            quickSort(nums, pivotLoc + 1, right);
        }
    }

    /**
     * 选择第一个值、中间值、最后一个值为中轴，这里选择最后一个值
     *  在[start,end]范围内，中轴左边的值都小于中轴，中轴右边的值都大于中轴
     *
     * @param nums 待排序数组
     * @param leftBound 排序起始位置
     * @param rightBound 排序终止位置
     * @return int 中轴下标
     */
    // 分区
    private static int partition(int[] nums, int leftBound, int rightBound) {
        int left = leftBound;
        int pivot = nums[rightBound];
        int right = rightBound - 1;
        while (left <= right) {// left <= right取等，[4,6]时进不去while
            while (left <= right && nums[left] <= pivot) left++;
            while (left <= right && nums[right] > pivot) right--;
            if (left < right) swap(nums, left, right);
        }
        swap(nums, left, rightBound);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new QuickSort().sortArray(new int[]{5, 2, 3, 1}));
    }
}