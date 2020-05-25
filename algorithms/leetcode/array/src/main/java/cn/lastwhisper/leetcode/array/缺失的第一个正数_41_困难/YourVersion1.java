package cn.lastwhisper.leetcode.array.缺失的第一个正数_41_困难;

class YourVersion1 {

    public int firstMissingPositive(int[] nums) {

        // 处理负数和0
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int absNum = Math.abs(nums[i]);
            if (absNum <= nums.length) {
                nums[absNum - 1] = -Math.abs(nums[absNum - 1]);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

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
        int[] arr = new int[]{2, 2};//1
        System.out.println(new YourVersion1().firstMissingPositive(arr));
    }
}