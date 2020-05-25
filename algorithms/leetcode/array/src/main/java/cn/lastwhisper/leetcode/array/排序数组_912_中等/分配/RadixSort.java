package cn.lastwhisper.leetcode.array.排序数组_912_中等.分配;

import org.junit.Assert;

class RadixSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  基数排序——稳定
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        int[][] bucket = new int[10][nums.length];
        // 桶元素计数器，记录每个桶中存放了多少数据
        int[] bucketElementCount = new int[10];
        // 原arr下标
        int arrIndex;
        // 除数
        int divisor = 1;
        // 最大值
        int max = nums[0];
        // 由于基数排序次数由最大数值的位数决定，所以需要求最大值
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }

        // 最大值得位数，即排序次数
        int maxLength = (max + "").length();
        for (int f = 0; f < maxLength; f++) {
            for (int num : nums) {
                // digitOfElement对应放在哪个bucket中
                int digitOfElement = num / divisor % 10;
                // 放在第digitOfElement个bucket的第bucketElementCount[digitOfElement]下标位置
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = num;
                // 下次放在bucket的第bucketElementCount[digitOfElement]++下标位置
                bucketElementCount[digitOfElement]++;
            }

            arrIndex = 0;
            // 将桶中数据放回原数组
            for (int i = 0; i < bucketElementCount.length; i++) {
                // 对应桶中有数据
                if (bucketElementCount[i] != 0) {
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        nums[arrIndex++] = bucket[i][j];
                    }
                }
                // 将桶元素计数器清空
                bucketElementCount[i] = 0;
            }
            divisor *= 10;
        }
        return nums;
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new RadixSort().sortArray(new int[]{5, 2, 3, 1}));
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 5}, new RadixSort().sortArray(new int[]{5, 1, 1, 2, 0, 0}));
    }
}