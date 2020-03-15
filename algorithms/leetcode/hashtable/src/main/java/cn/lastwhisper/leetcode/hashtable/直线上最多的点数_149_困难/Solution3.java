package cn.lastwhisper.leetcode.hashtable.直线上最多的点数_149_困难;

import java.util.HashMap;
import java.util.Map;

class Solution3 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/max-points-on-a-line/
     * -------------------------------------------------------------------
     * 思考：统计斜率相同的点
     * -------------------------------------------------------------------
     * 思路：
     *  Q:计算斜率时如何保证浮点数的精确度？
     *  A:将斜率换成x、y相关的字符串
     * -------------------------------------------------------------------
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * -------------------------------------------------------------------
     * 执行用时 :20 ms, 在所有 Java 提交中击败了68.23%的用户
     * 内存消耗 :36.2 MB, 在所有 Java 提交中击败了61.39%的用户
     */
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int res = 0;
        //遍历每个点
        for (int i = 0; i < points.length; i++) {
            int duplicate = 0;
            int max = 0;//保存经过当前点的直线中，最多的点
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                //求出分子分母
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                // 相同点
                if (x == 0 && y == 0) {
                    duplicate++;
                    continue;
                }
                //进行约分
                int gcd = gcd(x, y);
                x = x / gcd;
                y = y / gcd;
                String key = x + "@" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            //1 代表当前考虑的点，duplicate 代表和当前的点重复的点
            res = Math.max(res, max + duplicate + 1);
        }
        return res;
    }

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
        System.out.println(new Solution3().maxPoints(points));

        //System.out.println(new Solution3().gcdlcm(3,6));
    }
}

