package cn.lastwhisper.search;

import cn.lastwhisper.ArrayUtil;

/**
 *
 * @author lastwhisper
 */
public class FibonacciSearch {
    private static int count = 0;

    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArrByOrder(100);
        //int[] arr = {1, 1, 1, 1, 10, 89, 1000, 1234};
        System.out.println(fibonacciSearch(arr, 37));
        System.out.println("查找次数：" + count);
    }

    //因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契(黄金分割法)查找（递归）
     *
     * @param arr
     * @param target
     * @return int
     */
    public static int fibonacciSearch(int[] arr, int target) {
        return 0;
    }
}
