package cn.lastwhisper.leetcode.week.one183.将二进制表示减到1的步骤数_5377_中等;

import org.junit.Assert;

import java.math.BigInteger;
import java.util.LinkedList;

class Solution {

    public int numSteps(String s) {
        BigInteger num = convert(s);
        System.out.println(num.toString());
        BigInteger two = BigInteger.valueOf(2);
        int count = 0;
        while (num.compareTo(BigInteger.ONE) != 0) {
            if (num.remainder(two).compareTo(BigInteger.ZERO) == 0) {
                num = num.divide(two);
            } else {
                num = num.add(BigInteger.ONE);
            }
            count++;
        }
        return count;
    }

    private BigInteger convert(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i) - '0');
        }
        BigInteger sum = BigInteger.ZERO;
        int i = 0;
        BigInteger two = BigInteger.valueOf(2);
        while (!stack.isEmpty()) {
            BigInteger temp = BigInteger.valueOf(stack.pop());
            sum = sum.add(temp.multiply(two.pow(i++)));
        }
        return sum;
    }

    public static void main(String[] args) {
        //Assert.assertEquals(6, new Solution().numSteps("1101"));
        //Assert.assertEquals(20, new Solution().numSteps("111111001110101"));
        //Assert.assertEquals(85, new Solution().numSteps("1111011110000011100000110001011011110010111001010111110001"));
        Assert.assertEquals(85, new Solution().numSteps("1111110011101010110011100100101110010100101110111010111110110010"));
    }
}