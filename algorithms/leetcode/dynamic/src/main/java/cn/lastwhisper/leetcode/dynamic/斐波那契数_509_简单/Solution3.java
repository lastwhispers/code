package cn.lastwhisper.leetcode.dynamic.斐波那契数_509_简单;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/fibonacci-number/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：动态规划(自底向上)
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int fib(int N) {
        if (N < 2) {
            return N;
        }
        int[] arr = new int[N + 1];
        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= N; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[N];
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().fib(2));//1
        System.out.println(new Solution3().fib(10));//55
    }
}