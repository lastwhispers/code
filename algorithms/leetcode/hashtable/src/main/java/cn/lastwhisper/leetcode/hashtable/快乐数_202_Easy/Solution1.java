package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;

import java.util.HashSet;
import java.util.Set;

class Solution1 {
    /**
     * https://leetcode-cn.com/problems/happy-number/submissions/
     * 核心思路：直接按题意来
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        while (true) {
            int[] arr = split(n);
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i] * arr[i];
                if (i == arr.length - 1) {
                    System.out.printf("%d^2 = ", arr[i]);
                } else {
                    System.out.printf("%d^2 + ", arr[i]);
                }
            }
            System.out.println(sum);
            if (sum == 1) {
                return true;
            } else if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            n = sum;
        }
    }

    public int[] split(int num) {
        int bit, ten, hundred;
        if (num < 10) {
            return new int[]{num};
        } else if (num < 100) {
            bit = num % 10;
            ten = num / 10;
            return new int[]{bit, ten};
        } else {
            bit = num % 10;
            ten = num / 10 % 10;
            hundred = num / 100;
            return new int[]{bit, ten, hundred};
        }
    }
    public static void main(String[] args) {
        System.out.println(new Solution1().isHappy(18));
    }
}