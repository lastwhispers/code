package cn.lastwhisper.leetcode.array.数组值当下标.缺失的第一个正数_41_困难;

class Solution2 {

    /**
     * https://leetcode-cn.com/problems/first-missing-positive/
     * 核心思路：使用数组值当做下标
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     *
     */
    public int firstMissingPositive(int[] nums) {

        // 处理负数和0，置为大于输入数组长度的一个整数即可
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }

        // 将所有出现的正整数，且值在数组长度内，置为负数
        for (int i = 0; i < nums.length; i++) {
            int absNum = Math.abs(nums[i]);
            /*
             * 将[数组值-1]的值当做下标，并将该下标的值置为负，
             * 没有被置为负数的数组值，说明它没有后一位数
             *
             * absNum>nums.length，有三种情况
             *  1.负数
             *  2.0
             *  3.输入数组中本身就有一个很大的值（此时数组肯定不连续）
             */
            if (absNum <= nums.length) {
                /*
                 * 这个负数不能随便写，一定是-nums[absNum - 1]
                 *  比如nums={2,2}
                 *  情况一：nums[absNum - 1] = -Math.abs(nums[absNum - 1])
                 *      第一次循环nums={2,-2}，第二次循环还是nums={2,-2}
                 *  情况二：随便写一个负数，nums[absNum - 1] = -1
                 *      第一次循环nums={2,-1}，第二次循环还是nums={-1，-1}
                 *
                 */
                nums[absNum - 1] = -Math.abs(nums[absNum - 1]);
            }
        }
        /*
         * 处理不连续的数组和连续的数组
         * （1）长度为5的数组里面有一个100——此时缺失的数一定小于100，
         *      且为一个没有帮它置为负数的值
         * （2）长度为100的连续数组，最大值100——此时缺失的数一定为100+1
         */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // 处理空数组
        return nums.length + 1;
    }

    /**
     * 题目: 给定一个未排序的整数数组，找出其中没有出现的最小的正整数
     * 说明: 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
     */
    public static void main(String[] args) {
        //int[] arr = new int[]{1, 2, 0};//3
        //int[] arr = new int[]{3, 4, -1, 1};//2 //特殊情况一：输入数组中有负数
        //int[] arr = new int[]{7, 8, 9, 11, 12};//1  //特殊情况二：输入数组第一个数值大于1
        //int[] arr = new int[]{};//1  //特殊情况三：输入数组为空
        //int[] arr = new int[]{1, 2, 3, 10, 2147483647, 9};//1  //特殊情况四：输入数组值过大
        //int[] arr = new int[]{1};//2
        //int[] arr = new int[]{0};//1
        //int[] arr = new int[]{2,2};//1
        int[] arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};//10
        System.out.println(new Solution2().firstMissingPositive(arr));
    }
}