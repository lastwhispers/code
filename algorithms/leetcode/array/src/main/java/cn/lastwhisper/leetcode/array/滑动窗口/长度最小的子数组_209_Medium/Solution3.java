package cn.lastwhisper.leetcode.array.长度最小的子数组_209_Medium;

/**
 * @author lastwhisper
 */
public class Solution3 {
    /**
     * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
     * 核心思想：滑动窗口，两个指针从起始点向前推进，维护一个窗口
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (s <= 0 || nums == null) {
            throw new IllegalArgumentException("Illegal Argument");
        }
        // nums[l...r]是一个滑动窗口
        int l = 0, r = -1;
        int sum = 0;
        int res = nums.length + 1;
        // 窗口的左边界在数组范围内,则循环继续
        while (l < nums.length) {
            if (r < nums.length - 1 && sum < s) {
                //r未到头且sum<s，需要动态扩展窗口右边界
                r++;
                sum += nums[r];
            } else {
                // sum>s，需要动态缩小窗口左边界
                sum -= nums[l];
                l++;
            }
            if (sum >= s) {
                res = Math.min(res, r - l + 1);
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
        System.out.println((new Solution3()).minSubArrayLen(s, nums));
    }
}
