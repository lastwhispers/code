package cn.lastwhisper.leetcode.array.长度最小的子数组_209_Medium;

/**
 * 209. 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @author lastwhisper
 */
public class Solution4 {
    // 滑动窗口
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        // nums[l...r]是一个滑动窗口
        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length + 1;
        // 窗口的右边界在数组范围内,则循环继续
        while (r < nums.length - 1) {
            while (r < nums.length - 1 && sum < s) {
                r++;
                sum += nums[r];
            }
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
            while (l < nums.length && sum >= s) {
                sum -= nums[l];
                l++;
                if (sum >= s) {
                    res = Math.min(res, r - l + 1);
                }
            }
        }

        if (res == nums.length + 1) {
            res = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;
        System.out.println((new Solution4()).minSubArrayLen(s, nums));
    }

}
