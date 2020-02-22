package cn.lastwhisper.leetcode.dynamic.分割等和子集_416_中等;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/partition-equal-subset-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *   背包问题，在n个物品中选出一定物品，填满sum/2的背包
     *      dp[i][j]考虑将i个物品填满容量为j的背包
     *      dp[i][j]=dp[i-1][j] or dp[i][j-num[i]]
     * -------------------------------------------------------------------
     * 时间复杂度：O(NC)：这里 N 是数组元素的个数，C 是数组元素的和的一半。
     * 空间复杂度：O(NC)
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;

        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        // 外循环是物品
        for (int i = 1; i < len; i++) {
            // 内循环是容量
            for (int j = 0; j <= target; j++) {
                // 第i个物品重量num[i]刚好够，背包容量j
                if (nums[i] == j) {
                    dp[i][j] = true;
                } else if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len - 1][target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().canPartition(new int[]{1, 2, 5}));
        //System.out.println(new Solution3().canPartition(new int[]{1, 5, 11, 5}));
    }
}