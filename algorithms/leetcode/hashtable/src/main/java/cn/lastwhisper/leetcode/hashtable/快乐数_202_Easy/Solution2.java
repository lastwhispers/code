package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;


import java.util.HashSet;
import java.util.Set;

class Solution2 {

    /**
     * https://leetcode-cn.com/problems/happy-number/submissions/
     * 核心思路：hash
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        while (true) {
            int sum = 0, bit = 0;
            while (n > 0) {
                bit = n % 10;
                sum += bit * bit;
                if (n / 10 > 0) {
                    System.out.printf("%s^2 + ", bit);
                } else {
                    System.out.printf("%s^2 = ", bit);
                }
                n /= 10;
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

    public static void main(String[] args) {
        System.out.println(new Solution2().isHappy(1812));
    }
}