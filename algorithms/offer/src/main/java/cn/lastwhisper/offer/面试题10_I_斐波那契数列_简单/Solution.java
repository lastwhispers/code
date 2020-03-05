package cn.lastwhisper.offer.面试题10_I_斐波那契数列_简单;

class Solution {
    // 表示循环斐波那契？
    //public int fib(int n) {
    //    if (n < 2) return n;
    //    int item1 = 0;
    //    int item2 = 1;
    //    int sum = 0;
    //    for (int i = 2; i <= n; i++) {
    //        sum = (item1 + item2) % 1000000007;
    //        item1 = item2;
    //        item2 = sum;
    //    }
    //    return sum;
    //}
    public int fib(int n) {
        if (n < 2) return n;
        long item1 = 0;
        long item2 = 1;
        long sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (item1 + item2) % 1000000007;
            item1 = item2;
            item2 = sum;
        }
        return (int) sum;
    }
}