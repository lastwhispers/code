package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;

class Solution3 {
    /**
     * https://leetcode-cn.com/problems/happy-number/
     * 核心思路：快慢指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public boolean isHappy(int n) {
        int slow = n, fast = bitSquareSum(n);
        while (slow != fast) {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }
        return slow == 1;
    }

    public int bitSquareSum(int num) {
        int sum = 0;
        while (num > 0) {
            int bit = num % 10;
            sum += bit * bit;
            num = num / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().isHappy(18));
        //System.out.println(new Solution3().bitSquareSum(4328));
    }
}