package cn.cunchang.数组中的第K个最大元素_215_中等;

import cn.cunchang.array.ArrayUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }


    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution2().findKthLargest(ArrayUtil.createArrays(2, 1), 1));
        System.out.println(new Solution2().findKthLargest(ArrayUtil.createArrays(3, 2, 1, 5, 6, 4), 2));
    }

}