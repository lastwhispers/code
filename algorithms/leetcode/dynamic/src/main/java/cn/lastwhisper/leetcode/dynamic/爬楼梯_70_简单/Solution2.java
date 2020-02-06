package cn.lastwhisper.leetcode.dynamic.爬楼梯_70_简单;

import java.util.HashMap;
import java.util.Map;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/climbing-stairs/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归(自顶向下)+记忆化搜索
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    private Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        Integer result = map.getOrDefault(n, -1);
        if (result == -1) {
            result = climbStairs(n - 1) + climbStairs(n - 2);
            map.put(n, result);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().climbStairs(2));//2
        System.out.println(new Solution2().climbStairs(3));//3
        System.out.println(new Solution2().climbStairs(4));//5
    }
}