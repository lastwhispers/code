package cn.lastwhisper.leetcode.array.其他.颜色分类_75_Medium;

import java.util.Arrays;

public class Solution1 {

    /**
     * 题目：https://leetcode-cn.com/problems/sort-colors/
     * 计数排序
     * 核心思想：统计每一个数出现的频率
     * 时间复杂度：O(n)
     * 时间复杂度：O(1)
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        for (int i = 0; i < count[0]; i++) {
            nums[i] = 0;
        }
        for (int i = count[0]; i < count[0] + count[1]; i++) {
            nums[i] = 1;
        }
        for (int i = count[0] + count[1]; i < count[0] + count[1] + count[2]; i++) {
            nums[i] = 2;
        }
    }

    // 优化
    public void sortColors1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < 3; i++) {
            int itemCount = count[i];
            while (itemCount-- > 0) {
                nums[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        new Solution1().sortColors(arr);
        System.out.println(Arrays.toString(arr));
    }
}