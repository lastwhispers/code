package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;

class Solution3 {
    public boolean isHappy(int n) {
        int slow = n, fast = bitSquareSum(n,"fast");
        while (slow != fast) {
            slow = bitSquareSum(slow,"slow");
            fast = bitSquareSum(fast,"fast");
            fast = bitSquareSum(fast,"fast");
        }
        return slow == 1;
    }

    public int bitSquareSum(int num,String who) {
        int sum = 0;
        while (num > 0) {
            int bit = num % 10;
            sum += bit * bit;
            System.out.printf("%d^2 + ", bit);
            num = num / 10;

        }
        System.out.println(sum+"\t"+who);
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().isHappy(18));
        //System.out.println(new Solution3().bitSquareSum(4328));
    }
}