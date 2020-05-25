package cn.lastwhisper.leetcode.array.数组中的第K个最大元素_215_中等;

import org.junit.Assert;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
     * 编号：215
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：小顶堆
     *     利用小顶堆的特性（堆顶元素最小），先对前K个数组元素进行"原地"建小顶堆，建完小顶堆后，堆顶的元素最小，正好是这K个元素的第K大元素。
     * 然后遍历剩下的元素 nums[k] ~ nums[len-1]
     *      1、如果比堆顶元素小，跳过
     *      2、如果比堆顶元素大，和堆顶元素交换后重新堆化
     *     建堆 buildHeap 时间复杂度 O(K)，遍历剩下元素并且堆化 时间复杂度(N-K)*O(logK)，总体的时间复杂度 O(NlogK)
     * -------------------------------------------------------------------
     * 时间复杂度：O(NlogK)
     * 空间复杂度：O(1)
     */
    public int findKthLargest(int[] nums, int k) {
        //前K个元素原地建小顶堆
        buildHeap(nums, k);
        //遍历剩下元素，比堆顶小，跳过；比堆顶大，交换后重新堆化
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < nums[0]) continue;
            swap(nums, i, 0);
            heapify(nums, k, 0);
        }
        //K个元素的小顶堆的堆顶即是第K大元素
        return nums[0];
    }

    /**
     * 建堆函数
     * 从倒数第一个非叶子节点开始堆化，倒数第一个非叶子节点下标为 K/2-1
     */
    public void buildHeap(int[] a, int k) {
        for (int i = k / 2 - 1; i >= 0; i--) {
            heapify(a, k, i);
        }
    }

    /**
     * 堆化函数
     * 父节点下标i，左右子节点的下标分别为 2*i+1 和 2*i+2
     */
    public void heapify(int[] a, int k, int i) {
        //临时变量 minPos 用于存储最小值的下标，先假设父节点最小
        int minPos = i;
        while (true) {
            //和左子节点比较
            if (i * 2 + 1 < k && a[i * 2 + 1] < a[i]) minPos = i * 2 + 1;
            //和右子节点比较
            if (i * 2 + 2 < k && a[i * 2 + 2] < a[minPos]) minPos = i * 2 + 2;
            //如果minPos没有发生变化，说明父节点已经是最小了，直接跳出
            if (minPos == i) break;
            //否则交换
            swap(a, i, minPos);
            //父节点下标进行更新，继续堆化
            i = minPos;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        Assert.assertEquals(5, new Solution2().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
}