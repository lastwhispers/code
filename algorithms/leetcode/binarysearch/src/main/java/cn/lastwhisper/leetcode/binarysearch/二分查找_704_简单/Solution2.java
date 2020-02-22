package cn.lastwhisper.leetcode.binarysearch.二分查找_704_简单;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-search/
     * -------------------------------------------------------------------
     * 思考：
     *  二分搜索防加法溢出，遍历和递归两种写法
     * -------------------------------------------------------------------
     * 思路：
     *  递归写法
     * -------------------------------------------------------------------
     * 时间复杂度：O(logn)
     * 空间复杂度：O(logn)
     */
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1, 0);
    }

    public int binarySearch(int[] nums, int target, int start, int end, int middle) {
        if (end < start) {
            return -1;
        }
        middle = (end - start) / 2 + start;
        if (nums[middle] == target) {
            return middle;
        } else if (nums[middle] > target) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
        return binarySearch(nums, target, start, end, middle);
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        //int target = 2;
        System.out.println(new Solution2().search(nums, target));
    }
}