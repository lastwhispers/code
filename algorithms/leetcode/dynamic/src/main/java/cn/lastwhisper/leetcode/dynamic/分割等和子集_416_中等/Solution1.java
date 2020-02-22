package cn.lastwhisper.leetcode.dynamic.分割等和子集_416_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/partition-equal-subset-sum/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划
     *   背包问题，在n个物品中选出一定物品，填满sum/2的背包
     *  1、状态定义；
     *  2、状态转移方程；
     *  3、初始化；
     *  4、输出；
     *  5、思考状态压缩
     *      F(n,c)考虑将n个物品填满容量为c的背包
     *      F(i,c) = F(i-1,c)||F(i-1,c-w(i))
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*sum/2)
     * 空间复杂度：O(n*sum/2)
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }

        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    // 使用nums[0,index]，是否可以完全填充一个容量为sum的背包
    private boolean tryPartition(int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        if (index < 0 || sum < 0) {
            return false;
        }

        return tryPartition(nums, index - 1, sum) || tryPartition(nums, index - 1, sum - nums[index]);
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().canPartition(new int[]{1, 5, 11, 5}));
    }
}