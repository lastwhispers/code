package cn.lastwhisper.leetcode.hashtable.直线上最多的点数_149_困难;

import java.util.HashSet;
import java.util.Set;

class Solution2 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/max-points-on-a-line/
     * -------------------------------------------------------------------
     * 思考：虽然已经保证ij一定构成一条直线，但是ij会重复出现。
     * -------------------------------------------------------------------
     * 思路：基于思路1，使用Hash表记录暴力解法中已经出现的直线。
     *  利用"斜截式"求得直线的k、b，将k、b拼接放入Hash表
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 执行用时 :78 ms, 在所有 Java 提交中击败了7.91%的用户
     * 内存消耗 :39.2 MB, 在所有 Java 提交中击败了44.56%的用户
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

        Set<String> hash = new HashSet<>();

        int max = 0;
        for (int i = 0; i < points.length; i++) {
            // 防止数组下标相同i==j时，ij并不构成一根直线
            for (int j = i + 1; j < points.length; j++) {
                // 防止数组值相同arr[i]==arr[j]时，ij并不构成一根直线
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }

                String key = getK(points[i][0], points[i][1], points[j][0], points[j][1])
                        + "@"
                        + getB(points[i][0], points[i][1], points[j][0], points[j][1]);
                if (hash.contains(key)) {
                    continue;
                }

                hash.add(key);

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
     * 一个点是否在一条直线上
     */
    private boolean isExist(long x1, long y1, long x2, long y2, long x, long y) {
        return (y2 - y1) * (x - x2) == (y - y2) * (x2 - x1);
    }

    /**
     * 点斜式常数b
     */
    private double getB(int x1, int y1, int x2, int y2) {
        if (y2 == y1) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (x2 - x1) * (-y1) / (y2 - y1) + x1;
    }

    /**
     * 点斜式斜率k
     */
    private double getK(int x1, int y1, int x2, int y2) {
        if (x2 - x1 == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (y2 - y1) / (x2 - x1);
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
        System.out.println(new Solution2().maxPoints(points));

        //System.out.println(new Solution1().isExist(3, 3, 2, 2, 1, 1));
    }
}

