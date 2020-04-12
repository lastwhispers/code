package cn.lastwhisper.leetcode.array.排序.排序数组_912_中等.选择;

import org.junit.Assert;

class HeapSort {
    /**
     * 题目地址：https://leetcode-cn.com/problems/rectangle-overlap/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     *  堆排序——不稳定
     *  https://zh.wikipedia.org/wiki/%E5%A0%86%E6%8E%92%E5%BA%8F
     *
     *      大顶堆升序，小顶堆降序
     * -------------------------------------------------------------------
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     */
    public int[] sortArray(int[] nums) {
        // 1. 满二叉树从右向左、从下往上构建大顶堆；完全二叉树从左向右、从下往上构建大顶堆；
        // i--意味着，从下到上，每一次都是[i,arr.length]的数组（二叉树）进行重构大顶堆
        // 将整个数组构建成完整的大顶堆，此时nums[0]是数组最大值
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        // 2. 交换堆顶元素与末尾元素，调整堆结构
        for (int j = nums.length - 1; j >= 0; j--) {
            int temp = nums[j];
            nums[j] = nums[0];
            nums[0] = temp;
            adjustHeap(nums, 0, j);
        }

        return nums;
    }


    /**
     * 将一个数组(二叉树), 调整成一个大顶堆
     * 功能： 将以i对应的非叶子节点的树调整成大顶堆
     * 举例：
     *     i = 1 => {4, 6, 8, 5, 9} => {4, 9, 8, 5, 6}
     *     i = 0 => {4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public void adjustHeap(int[] arr, int i, int length) {
        // 当前非叶子节点值
        int temp = arr[i];
        //  k = 2*i+1是i节点的左子节点，k+1是右子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            //  找左右子节点最大的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {//子节点大于父节点
                arr[i] = arr[k];//将较大的子节点赋值到当前节点（父节点）
                i = k;//记录子节点的下标，为了最后 arr[i] = temp;
            } else {
                break;//从下至上，下面已经是大顶堆
            }
        }
        //i = k; arr[i]此时已经是子节点的位置了，前面父节点已经拿到了子节点的值了，这里要将子节点的值赋为父节点的值
        // 当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶（局部）
        arr[i] = temp;
    }

    public static void main(String[] args) {
        Assert.assertArrayEquals(new int[]{1, 2, 3, 5}, new HeapSort().sortArray(new int[]{5, 2, 3, 1}));
        Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 2, 5}, new HeapSort().sortArray(new int[]{5, 1, 1, 2, 0, 0}));
    }
}