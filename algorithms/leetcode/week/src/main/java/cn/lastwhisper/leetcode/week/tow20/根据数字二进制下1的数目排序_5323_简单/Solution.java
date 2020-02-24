package cn.lastwhisper.leetcode.week.tow20.根据数字二进制下1的数目排序_5323_简单;

import java.util.Arrays;

class Solution {

    public int[] sortByBits(int[] arr) {
        Integer[] nums = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }
        Arrays.sort(nums, (o1, o2) -> {
            int bitCountA = Integer.bitCount(o1);
            int bitCountB = Integer.bitCount(o2);
            // 相同按原数，不同按位数
            return bitCountA == bitCountB ? o1 - o2 : bitCountA - bitCountB;
        });
        for (int i = 0; i < arr.length; i++) {
            arr[i] = nums[i];
        }
        return arr;
    }

    private int bitCount(int n) {
        int count = 0;
        while (n != 0) {
            // n=10011101,n-1=10011100
            // &全1则1，否则0
            // n & (n - 1)=10011101&10011100=10011100
            // n=10011100
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        //int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        System.out.println(Arrays.toString(new Solution().sortByBits(arr)));
    }
}