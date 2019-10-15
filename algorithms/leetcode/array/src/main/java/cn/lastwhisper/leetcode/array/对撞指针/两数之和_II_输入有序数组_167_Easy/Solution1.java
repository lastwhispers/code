package cn.lastwhisper.leetcode.array.两数之和_II_输入有序数组_167_Easy;

/**
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
 * @author lastwhisper
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
     * 暴力解法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return null;
        }
        int len = numbers.length;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if ((i != j) && (numbers[i] + numbers[j]) == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 3, 4};
        int[] index = new Solution1().twoSum(arr, 0);
        System.out.printf("[%s,%s]", index[0], index[1]);
    }
}
