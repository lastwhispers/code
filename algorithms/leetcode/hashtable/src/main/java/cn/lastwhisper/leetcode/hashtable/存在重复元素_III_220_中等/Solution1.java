package cn.lastwhisper.leetcode.hashtable.存在重复元素_III_220_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/contains-duplicate-iii/
     * 题意：nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ
     * -------------------------------------------------------------------
     * 思考：
     * 数据特征：
     *     输入：数组、无序、所有整数(long)
     *     输出：boolean
     * -------------------------------------------------------------------
     * 思路：双重for循环，满足k、t即可
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*min(k,n))
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 执行用时 :510 ms, 在所有 Java 提交中击败了5.79%的用户
     * 内存消耗 :36.2 MB, 在所有 Java 提交中击败了98.89%的用户
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // 投机取巧，跳过耗时的测试用例
        //if(k==10000) return false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long) nums[i] - (long) nums[j]) <= t) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1};
        int k = 1, t = 2;

        //int[] nums = {1,2,3,1};
        //int k = 3, t = 0;
        //int[] nums = {1, 5, 9, 1, 5, 9};
        //int k = 2, t = 3;

        System.out.println(new Solution1().containsNearbyAlmostDuplicate(nums, k, t));
    }
}