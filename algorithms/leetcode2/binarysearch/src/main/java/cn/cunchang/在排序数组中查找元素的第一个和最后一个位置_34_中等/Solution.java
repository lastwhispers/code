package cn.cunchang.在排序数组中查找元素的第一个和最后一个位置_34_中等;

class Solution {
    /**
     * 找目标值的最左边和目标值+1的最左边
     * 缺点，小数不好使了
     *
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = search(nums, target);
        // l >= nums.length ==》超出右边界 searchRange(new int[]{2,2}, 3)
        // nums[l] != target ==》找不到 searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)
        if (l >= nums.length || nums[l] != target) {
            return new int[]{-1, -1};
        }
        int r = search(nums, target + 1) - 1;
        return new int[]{l, r};
    }


    private int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            // 这里与无重复二分不同，=r也减少为了搜索最左边的target
            if (nums[mid] >= target) {
                r = mid - 1;
            } else if (nums[mid] < target) {
                l = mid + 1;
            }
        }
        return l;
    }
}