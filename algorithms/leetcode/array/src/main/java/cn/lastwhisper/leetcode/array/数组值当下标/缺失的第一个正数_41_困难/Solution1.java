package cn.lastwhisper.leetcode.array.数组值当下标.缺失的第一个正数_41_困难;

class Solution1 {

    /**
     * https://leetcode-cn.com/problems/first-missing-positive/
     * 核心思路：输入数组值映射到数组下标，修改下标对应的值标识该数组值出现过，
     *  最后统计数组第一个未标识的下标即为结果
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * -------------------------------------------------------------------
     * 此思路无法应对输入数组中存在一个很大的值
     */
    public int firstMissingPositive(int[] nums) {
        //特殊情况三：输入数组为空
        if (nums.length <= 0) {
            return 1;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            // 特殊情况一：数组中有负数
            if (nums[i] < 0) {
                nums[i] = 0;
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int[] arr = new int[max + 2];
        for (int num : nums) {
            arr[num]++;
        }

        for (int i = 0; i < arr.length; i++) {
            // 特殊情况二：输入数组第一个数值大于1
            if (i == 0 && arr[i] == 0) {
                continue;
            }
            if (arr[i] == 0) {
                return i;
            }
        }

        return -1;
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
        int[] arr = new int[]{1, 2, 3, 10, 2147483647, 9};//1  //特殊情况四：输入数组值过大
        System.out.println(new Solution1().firstMissingPositive(arr));
        //System.out.println(Integer.MAX_VALUE==2147483647);
    }
}