package cn.lastwhisper.leetcode.recurionbacktracking.二进制手表_401_简单;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printList;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-watch/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：暴力
     *  表中的十位数：“1、2、4、8”、“1、2、4、8、16、32”，在二进制中都只有一位为1，12小时可以使用这十个最基本的数组成。
     *  num表示这十位数出现几个，实际上出现几个就有几个二进制的1。
     *  从0:00-》11:59 直接判断小时位出现的1个数+分秒位出现的1个数
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                // num=hour二进制为1的数量+second二进制为1的数量
                if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
                    result.add(String.format("%d:%02d", i, j));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        printList(new Solution1().readBinaryWatch(3));
        //System.out.println(Integer.bitCount(4));
    }
}