package cn.lastwhisper.leetcode.array.错误的集合_645_简单;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/set-mismatch/
     * -------------------------------------------------------------------
     * 数据特征：
     *  输入：数组、1~n整数、无序、存在重复数据、不一定包含丢失数据
     *  输出：数组[重复数据,丢失数据]、1~n+1整数、
     * -------------------------------------------------------------------
     * 思路：
     *  使用两个set集合，set1找出重复数据，set2记录1~length+1，
     *  set2.removeAll(set1)得到丢失数据。
     * 特殊情况：[1,1]
     * -------------------------------------------------------------------
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] findErrorNums(int[] nums) {
        if (nums.length == 2) {
            if (nums[0] == 1 && nums[1] == 1) return new int[]{1, 2};
        }

        Set<Integer> allSet = new HashSet<>();
        Set<Integer> numsSet = new HashSet<>();
        int duplicate = -1, miss = -1;
        for (Integer num : nums) {
            // 找到重复，并将所有元素放到numsSet
            if (numsSet.contains(num)) {
                duplicate = num;
            } else {
                numsSet.add(num);
            }

        }
        for (int i = 1; i <= nums.length + 1; i++) {
            allSet.add(i);
        }
        allSet.removeAll(numsSet);
        for (Integer num : allSet) {
            miss = num;
            break;
        }

        return new int[]{duplicate, miss};
    }

    public static void main(String[] args) {
        // example
        //int[] arr = {1, 2, 2, 4};//2,3
        //int[] arr = {2, 2};//2,1
        // error example
        //int[] arr = {3, 2, 2};//2,1
        //int[] arr = {1, 1};//1,2
        int[] arr = {1, 5, 3, 2, 2, 7, 6, 4, 8, 9};
        System.out.println(Arrays.toString(new Solution1().findErrorNums(arr)));
    }
}