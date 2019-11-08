package cn.lastwhisper.leetcode.array.其他.数组中的第K个最大元素_215_Medium;

class Solution2 {
    /**
     *  利用堆排序思想
     *  O(n)桶排思想
     */
    public int findKthLargest(int[] nums, int k) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i : nums) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int n = max - min;
        int[] bucket = new int[n + 1];
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i] - min;
            bucket[tmp]++;
        }
        for (int i = n; i >= 0; i--) {
            if (bucket[i] > 0)
                k -= bucket[i];
            if (k <= 0)
                return i + min;
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int kthLargest = new Solution2().findKthLargest(arr, k);
        System.out.println(kthLargest);
    }
}