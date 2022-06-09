package cn.cunchang.两数之和_II_输入有序数组_167_简单;

import java.util.Arrays;

class Solution {
    /**
     * 思考：
     * -------------------------------------------------------------------
     * 思路：
     * "非递减顺序排列"表示升序
     * p1指向头，p2指向尾；num[p1]+num[p2]和target比会有三种情况；
     * num[p1]+num[p2]==target说明p1、p2就是两数之和
     * num[p1]+num[p2]>target说明p2大了，p2-1再试试
     * num[p1]+num[p2]<target说明p1小了，p1+1再试试
     * 直至两个指针重合，说明没有合适的两数之和
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int[] twoSum(int[] numbers, int target) {
        int p1 = 0, p2 = numbers.length - 1;
        while (p1 < p2) {
            int tmp = numbers[p1] + numbers[p2];
            if (tmp > target) {
                p2--;
            } else if (tmp < target) {
                p1++;
            } else {
                break;
            }
        }
        return new int[]{p1+1, p2+1};
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(
                new Solution().twoSum(numbers, target)));
    }

}