package cn.lastwhisper.offer.interview_10;

/**
 * 面试题10：斐波那契数列
 * 青蛙跳台阶问题
 * @author cn.lastwhisper
 */
public class Solution_10 {
    public static void main(String[] args) {
        Solution_10 solution = new Solution_10();
        //long fibonacci = solution.fibonacci_recursion(100);
        //long fibonacci = solution.fibonacci_iterative(1);
        long fibonacci = solution.fibonacci_last_recursion(5,0,1);
        System.out.println(fibonacci);
    }

    //递归求斐波那契数列
    public long fibonacci_recursion(long n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci_recursion(n - 1) + fibonacci_recursion(n - 2);
    }

    // 递归变循环
    public long fibonacci_iterative(long n) {
        if(n==0||n==1)return n;
        long item1 = 0;
        long item2 = 1;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum = item1 + item2;
            item1 = item2;
            item2 = sum;
        }
        return sum;
    }

    // 尾递归
    public long fibonacci_last_recursion(long n, long rt1, long rt2) {
        return n == 0 ? rt1 : fibonacci_last_recursion(n - 1, rt2, rt1 + rt2);
    }


}
