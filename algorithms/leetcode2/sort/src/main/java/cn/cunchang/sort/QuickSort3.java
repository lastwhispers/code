package cn.cunchang.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lastwhisper
 */
public class QuickSort3 {
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
//        int[] arr = {2, 10, 8, 1000};
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        int[] arr = {-9, 78, 0, 23, 60, 70};
//        int[] arr = {9, 0, -567};
        int[] arr = {49, 38, 65, 97, 76, 13, 27};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIdx = partition(arr, left, right);
        // 此时分区pivotIdx左边值小于等于arr[pivotIdx]，分区右边大于arr[pivotIdx]
        // 对[left,pivotIdx - 1]再次分区
        sort(arr, left, pivotIdx - 1);
        // 对[pivotIdx + 1,right]再次分区
        sort(arr, pivotIdx + 1, right);
    }

    /**
     * @param nums  待分区数组
     * @param left  待分区左边界
     * @param right 待分区右边界
     * @return pivotIdx 中轴索引
     */
    private static int partition(int[] nums, int left, int right) {
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, randomIndex);

        // all in nums[left + 1..le) <= pivot;
        // all in nums(ge..right] >= pivot;
        int pivot = nums[left];
        int start = left + 1;
        int end = right;

        while (true) {
            while (start <= end && nums[start] < pivot) {
                start++;
            }
            while (start <= end && nums[end] > pivot) {
                end--;
            }
            if (start >= end) {
                break;
            }
            swap(nums, start, end);
            start++;
            end--;
        }

        swap(nums, left, end);
        return end;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
