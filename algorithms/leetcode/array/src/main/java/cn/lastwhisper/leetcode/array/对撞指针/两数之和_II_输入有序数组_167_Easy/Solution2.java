package cn.lastwhisper.leetcode.array.对撞指针.两数之和_II_输入有序数组_167_Easy;

/**
 * @author lastwhisper
 */
public class Solution2 {

    /**
     * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 将差值放入二分查找剩余数组
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        for (int index1 = 0; index1 < numbers.length; index1++) {
            int differ = target - numbers[index1];
            int index2 = binarySearch(numbers, index1 + 1, numbers.length - 1, differ);
            if (index2 != -1) {
                return new int[]{index1 + 1, index2 + 1};
            }
        }
        throw new IllegalStateException("The input has no solution");
    }

    public int binarySearch(int[] arr, int l, int r, int target) {
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target)
                return mid;
            if (target > arr[mid])
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 9, 56, 90};
        int[] index = new Solution2().twoSum(arr, 8);
        System.out.printf("[%s,%s]", index[0], index[1]);
    }
}
