package cn.lastwhisper.leetcode.recurionbacktracking.二进制手表_401_简单;

import java.util.ArrayList;
import java.util.List;

import static cn.lastwhisper.leetcode.common.print.PrintUtils.printList;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/binary-watch/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：树形问题——递归回溯——剪枝
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*n!)
     * 空间复杂度：O(n*n!)
     */
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        dfs(num, 0, 0, new int[10], result);
        return result;
    }

    /**
     * C(10,num) 问题
     * @param num 未被点亮的灯数
     * @param index 递归的起始值
     * @param stat 基础的十个数，0表示未点亮，1表示点亮
     */
    public void dfs(int num, int index, int len, int[] stat, List<String> result) {
        // 灯都被点亮过了
        if (num == len) {
            int hour = stat[0] + 2 * stat[1] + 4 * stat[2] + 8 * stat[3];
            int minute = stat[4] + 2 * stat[5] + 4 * stat[6] + 8 * stat[7] + 16 * stat[8] + 32 * stat[9];
            if (hour < 12 && minute < 60) {
                result.add(String.format("%d:%02d", hour, minute));
            }
            return;
        }
        // 剪枝  i<=9 ----> i<=9-(num-len)+1
        for (int i = index; i <= (9 - (num - len) + 1); i++) {
            stat[i] = 1;
            dfs(num, i + 1, len + 1, stat, result);
            stat[i] = 0;
        }
    }

    public static void main(String[] args) {
        printList(new Solution3().readBinaryWatch(2));
    }
}