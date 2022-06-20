package cn.cunchang.搜索旋转排序数组_33_中等;

import org.junit.Assert;

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        // 1、找升序部分对升序部分进行二分
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[l]) {
                // 4,5,6,7,0,1,2；7>=4；说明[l,mid]是升序的
                // target在[l,mid]内，对[l,mid]进行二分
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // 4,5,6,0,1,2；0<4；说明说明[mid,r]是升序的
                // target在[mid,r]内，对[mid,r]进行二分
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Assert.assertEquals(5, new Solution().search(new int[]{4, 5, 6, 7, 8, 0, 1, 2, 3}, 0));
//        Assert.assertEquals(4, new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
//        Assert.assertEquals(-1, new Solution().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));


    }
}