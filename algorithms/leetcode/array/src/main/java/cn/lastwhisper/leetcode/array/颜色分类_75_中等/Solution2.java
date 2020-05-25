package cn.lastwhisper.leetcode.array.颜色分类_75_中等;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/sort-colors/
     * 编号：75
     * -------------------------------------------------------------------
     * 思考：
     *  数据特征：只有0,1,2
     * -------------------------------------------------------------------
     * 思路：
     *   三路快速排序的思想，对整个数组只遍历了一遍
     *  （1）双指针 left=-1，right=n
     *  （2）下标 i 遍历数组，num[i]==1，i++
     *  （3）num[i]==0，将 0 移动到数组最前位置，swap(num,++left,i++)
     *      i++，即使 num[++left]==0，这次的交换和位移也是正确的
     *  （4）num[i]==2，将 2 移动到数组最后位置，swap(num,--right,i)
     *      i不变，因为有可能num[--right]==2
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public void sortColors(int[] nums) {
        int left = -1, right = nums.length;
        for (int i = 0; i < right; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                swap(nums, ++left, i++);
            } else if (nums[i] == 2) {
                swap(nums, --right, i);
            }
        }
    }

    public void swap(int[] num, int i, int j) {
        if (i != j) {
            int temp = num[j];
            num[j] = num[i];
            num[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 0, 2, 1, 1, 0};
        new Solution2().sortColors(arr);
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }
}