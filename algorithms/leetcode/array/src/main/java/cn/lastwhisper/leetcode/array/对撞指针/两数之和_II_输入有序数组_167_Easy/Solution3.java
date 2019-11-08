package cn.lastwhisper.leetcode.array.对撞指针.两数之和_II_输入有序数组_167_Easy;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class Solution3 {
    /**
     * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 对撞指针（前提数组有序）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int left = numbers[l];
            int right = numbers[r];
            if ((left + right) == target) {
                return new int[]{l + 1, r + 1};
            } else if ((left + right) > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution3().twoSum(new int[]{0, 0, 3, 4}, 0)));
        System.out.println(Arrays.toString(new Solution3().twoSum(new int[]{1, 7, 17, 2, 6, 3, 14}, 20)));
    }
}
