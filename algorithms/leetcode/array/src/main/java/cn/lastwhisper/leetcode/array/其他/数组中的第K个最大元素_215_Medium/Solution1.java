package cn.lastwhisper.leetcode.array.其他.数组中的第K个最大元素_215_Medium;

class Solution1 {
    /**
     *  利用快排partition思想，将pivot放置在了其正确的位置上的性质
     */
    public int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    public int partition(int[] nums, int leftBound, int rightBound, int k) {
        int i = leftBound;
        int pivot = nums[leftBound];
        int j = rightBound - 1;
        while (i <= j) {
            while (i <= j && nums[i] <= pivot) {
                i++;
            }
            while (i <= j && nums[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        //swap(nums, i, rightBound);
        if (i == k) {
            return pivot;
        } else if (i > k) {
            return partition(nums, leftBound, i - 1, k);
        } else {
            return partition(nums, i + 1, rightBound, k);
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;
        //int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        //int k = 4;

        int kthLargest = new Solution1().findKthLargest(arr, k);
        System.out.println(kthLargest);
    }
}