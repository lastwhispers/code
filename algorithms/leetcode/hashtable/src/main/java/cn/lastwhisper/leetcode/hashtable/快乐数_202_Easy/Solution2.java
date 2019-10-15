package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution1 {

    /**
     * https://leetcode-cn.com/problems/happy-number/
     *
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        while (true) {
            String[] arr = split(n);
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += Integer.parseInt(arr[i]) * Integer.parseInt(arr[i]);
                if (i == arr.length - 1) {
                    System.out.printf("%s^2 = ", arr[i]);
                } else {
                    System.out.printf("%s^2 + ", arr[i]);
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

    public String[] split(int num) {
        String numStr = String.valueOf(num);
        return numStr.split("");
    }

    public static void main(String[] args) {
        System.out.println(new Solution1().isHappy(1812));
    }
}