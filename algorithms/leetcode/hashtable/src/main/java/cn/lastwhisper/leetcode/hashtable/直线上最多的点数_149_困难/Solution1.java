package cn.lastwhisper.leetcode.hashtable.直线上最多的点数_149_困难;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/max-points-on-a-line/
     * -------------------------------------------------------------------
     * 思路：暴力解法，任意取两个点构成一条直线，判断其它点在不在这个直线上（斜率）
     * -------------------------------------------------------------------
     * 思考：
     *  1、统计斜率相同且点不同的点数，并输出最大的点数
     *  2、Q:计算斜率时如何保证浮点数的精确度？
     *     A:(1)将除法意义转为乘法
     *          Q:如何保证乘法不溢出？(65535*65535)
     *          A:long、BigInteger
     *       (2)将直接除法转为求分子分母同时除以(分子分母的)最大公约数后，等式两边分子分母是否同时相等问题
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 执行用时 :24 ms, 在所有 Java 提交中击败了42.54%的用户
     * 内存消耗 :34.5 MB, 在所有 Java 提交中击败了82.67%的用户
     */
    public int maxPoints(int[][] points) {
        // 特殊情况一:0、1、2个点，直接返回点数
        if (points.length < 3) {
            return points.length;
        }

        // 特殊情况二:全为相同的点，直接返回点数
        int equalNum = 0;
        for (; equalNum < points.length - 1; equalNum++) {
            if (points[equalNum][0] != points[equalNum + 1][0] || points[equalNum][1] != points[equalNum + 1][1]) {
                break;
            }
        }
        if (equalNum == points.length - 1) {
            return points.length;
        }

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            // 防止数组下标相同i==j时，ij并不构成一根直线
            for (int j = i + 1; j < points.length; j++) {
                // 防止数组值相同arr[i]==arr[j]时，ij并不构成一根直线
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }
                // 前两层循环构成直线
                int record = 2;
                for (int k = 0; k < points.length; k++) {
                    // 防止数组下标相同k==i或者k==j时，仅剩两个点(i=0j=2k=0、i=1j=2k=2)
                    if (k != i && k != j) {
                        if (isExist(points[i][0], points[i][1], points[j][0]
                                , points[j][1], points[k][0], points[k][1])) {
                            record++;
                        }
                    }
                }
                if (record > max) {
                    max = record;
                }
            }
        }
        // 加上直线本身的两个点
        return max;
    }

    /**
     * 判断一个点是否在一条直线上
     */
    //private boolean isExist(int x1, int y1, int x2, int y2, int x, int y) {
    //    return (y2 - y1) * (x - x2) == (y - y2) * (x2 - x1);
    //}

    private boolean isExist(long x1, long y1, long x2, long y2, long x, long y) {
        return (y2 - y1) * (x - x2) == (y - y2) * (x2 - x1);
    }

    //private boolean isExist(int x1, int y1, int x2, int y2, int x, int y) {
    //    BigInteger x11 = BigInteger.valueOf(x1);
    //    BigInteger x22 = BigInteger.valueOf(x2);
    //    BigInteger y11 = BigInteger.valueOf(y1);
    //    BigInteger y22 = BigInteger.valueOf(y2);
    //    BigInteger x0 = BigInteger.valueOf(x);
    //    BigInteger y0 = BigInteger.valueOf(y);
    //    return y22.subtract(y11).multiply(x0.subtract(x22)).equals(y0.subtract(y22).multiply(x22.subtract(x11)));
    //}

    //private boolean isExist(int x1, int y1, int x2, int y2, int x, int y) {
    //    int g1 = gcdlcm(y2 - y1, x2 - x1);
    //    if(y == y2 && x == x2){
    //        return true;
    //    }
    //    int g2 = gcdlcm(y - y2, x - x2);
    //    return (y2 - y1) / g1 == (y - y2) / g2 && (x2 - x1) / g1 == (x - x2) / g2;
    //}


    /**
     * 求a、b最大公约数
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }


    public static void main(String[] args) {
        // example
        //int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        // error example
        //int[][] points = {{0, 0}};
        //int[][] points = {{0, 0}, {0, 0}};
        //int[][] points = {{0, 0}, {0, 0}, {0, 0}};
        //int[][] points = {{0, 0}, {1, 65536}, {65536, 0}};
        int[][] points = {{1, 1}, {1, 1}, {2, 3}};
        System.out.println(new Solution1().maxPoints(points));

        //System.out.println(new Solution1().isExist(3, 3, 2, 2, 1, 1));
    }
}

