package cn.cunchang.在排序数组中查找元素的第一个和最后一个位置_34_中等;

import java.util.Arrays;

class Solution2 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = lowerBound(nums, target);
        // l >= nums.length ==》超出右边界 searchRange(new int[]{2,2}, 3)
        // nums[l] != target ==》找不到 searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)
        if (l >= nums.length || nums[l] != target) {
            return new int[]{-1, -1};
        }
        int r = upperBound(nums, target) - 1;
        return new int[]{l, r};
    }

    private int upperBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else if (nums[mid] <= target) {
                // 这里与无重复二分不同，=时l也增加为了缩小左边，搜索最右边的target
                l = mid + 1;
            }
        }
        return l;
    }

    private int lowerBound(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            // 这里与无重复二分不同，=时r也减少为了缩小右边，搜索最左边的target
            // 这里有个问题就是，会不会跳过目标值比如 {5, 6, 7, 8, 9, 10}, 7；这里会跳过的
            // 但是l = mid + 1又加回来
            if (nums[mid] >= target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(new Solution2().
//                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
//        System.out.println(Arrays.toString(new Solution2().
//                searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
//        System.out.println(Arrays.toString(new Solution2().
//                searchRange(new int[]{2, 2}, 3)));
        System.out.println(Arrays.toString(new Solution2().
                searchRange(new int[]{5, 6, 7, 8, 9, 10}, 7)));
//        System.out.println(Arrays.toString(new Solution2().
//                searchRange(new int[]{5, 6, 7, 8, 9, 10}, 10)));
    }
}