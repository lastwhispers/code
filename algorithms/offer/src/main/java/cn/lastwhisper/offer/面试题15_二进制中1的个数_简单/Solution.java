package cn.lastwhisper.offer.面试题15_二进制中1的个数_简单;

class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
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

    public static void main(String[] args){
        System.err.println(new Solution().hammingWeight(00000000000000000000000000001011));
        System.err.println(new Solution().hammingWeight(00000000000000000000000010000000));
        //System.err.println(new Solution().hammingWeight(11111111111111111111111111111101));
    }

}