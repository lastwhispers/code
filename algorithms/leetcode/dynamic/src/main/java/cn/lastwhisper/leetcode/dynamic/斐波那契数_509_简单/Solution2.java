package cn.lastwhisper.leetcode.dynamic.斐波那契数_509_简单;

import java.util.HashMap;
import java.util.Map;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+记忆化搜索(自顶向下)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    private Map<Integer, Integer> map = new HashMap<>();

    public int fib(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }

        Integer result = map.getOrDefault(N, -1);
        if (result == -1) {
            result = fib(N - 1) + fib(N - 2);
            map.put(N, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().fib(10));
    }
}