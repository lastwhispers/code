package cn.cunchang.数组中的第K个最大元素_215_中等;

import cn.cunchang.array.ArrayUtil;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // topk的分区的位置
        int targetIdx = nums.length - k;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            // 中轴索引值
            int pivotIdx = partition(nums, left, right);
            if (targetIdx > pivotIdx) {
                // 说明要往右边分
                left = pivotIdx + 1;
            } else if (targetIdx < pivotIdx) {
                // 说明要往左边分
                right = pivotIdx - 1;
            } else {
                // 说明分区分到这个topk了
                return nums[pivotIdx];
            }

        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        // 选最后一个值作为中轴
        int pivot = nums[right];
        // 所依right要减一
        int start = left, end = right - 1;
        while (start <= end) {
            // 寻找左边大于pivot
            while (start <= end && nums[start] <= pivot) {
                start++;
            }
            // 寻找右边小于等于pivot
            while (start <= end && nums[end] > pivot) {
                end--;
            }
            if (start <= end) {
                swap(nums, start, end);
            }
        }
        // 此时[left,start-1]的值一定小于pivot=nums[right]
        // start一定大于right，所以将start与right交互
        swap(nums, start, right);

        return start;
    }

    private void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findKthLargest(ArrayUtil.createArrays(2,1), 1));
//        System.out.println(new Solution().findKthLargest(ArrayUtil.createArrays(3, 2, 1, 5, 6, 4), 2));
    }

}