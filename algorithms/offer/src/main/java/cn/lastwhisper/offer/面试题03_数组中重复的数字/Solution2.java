package cn.lastwhisper.offer.面试题03_数组中重复的数字;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     * -------------------------------------------------------------------
     * 思考：
     *  1、hash查重
     *  2、找规律(桶思想)
     * -------------------------------------------------------------------
     * 思路：长度n的数组，数字范围0～n-1。无重复：长度4的数组，nums={3,1,0,2}
     *  重复：长度4的数组，nums={3,2,0,2}
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            /*
             * 内循环
             *  1、将i==nums[i]，{3,2,0,2}==》{0,2,2,3}
             *  2、检查重复;i=1，nums[i] == nums[nums[i]]
             */
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    int temp = nums[i];
                    // 必须用nums[temp]，不能用nums[nums[i]]
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().findRepeatNumber(new int[]{3, 2, 0, 2}));
    }
}