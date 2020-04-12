package cn.lastwhisper.leetcode.array.排序.排序数组_912_中等.分配;

class CountSort {

    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  计数排序——稳定
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] sortArray(int[] nums) {
        int max = -50001, min = 50001;
        for (int num : nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        int[] counter = new int[max - min + 1];
        for (int num : nums) {
            counter[num - min]++;
        }

        int idx = 0;
        for (int num = min; num <= max; num++) {
            int cnt = counter[num - min];
            while (cnt-- > 0) {
                nums[idx++] = num;
            }
        }
        return nums;
    }
}