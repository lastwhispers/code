package cn.lastwhisper.leetcode.array.滑动窗口.长度最小的子数组_209_Medium;

/**
 * 209. 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @author lastwhisper
 */
public class Solution2 {
    // 优化暴力解法

    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        // nums=>  2, 3, 1, 2, 4, 3
        // sum=>0, 2, 5, 6, 8, 12,7
        // sums[i]存放nums[0...i-1]的和
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int res = nums.length + 1;
        for (int l = 0; l < nums.length; l++) {
            for (int r = l; r < nums.length; r++) {
                // sum[r + 1] - sum[l]快速求出nums[l...r]之和
                if (sum[r + 1] - sum[l] >= s) {
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
        System.out.println((new Solution2()).minSubArrayLen(s, nums));
    }
}
