package cn.lastwhisper.leetcode.array.数组中的第K个最大元素_215_Medium;

class Solution1 {
    /**
     *  利用快排partition思想，将pivot放置在了其正确的位置上的性质
     */
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    public int partition(int[] nums, int leftBound, int rightBound, int k) {
        int left = leftBound;
        int right = rightBound;
        int pivot = nums[leftBound];
        while (left < right) {
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            while (left < right && nums[right] > pivot) {
                right--;
            }
            if (left < right) swap(nums, left, right);
        }
        if (left == k) return pivot;
        else if (left > k) return partition(nums, leftBound, left - 1, k);
        else return partition(nums, left + 1, rightBound, k);
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        int kthLargest = new Solution1().findKthLargest(arr, k);
        System.out.println(kthLargest);
    }
}