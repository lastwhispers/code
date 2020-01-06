package cn.lastwhisper.leetcode.hashtable.回旋镖的数量_447_简单;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/number-of-boomerangs/
     * -------------------------------------------------------------------
     * 思考：计算两点问题开根号时会有精度问题
     * -------------------------------------------------------------------
     * 思路：
     * -------------------------------------------------------------------
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     */
    public int numberOfBoomerangs(int[][] points) {
        int res = 0;

        // record中存储 点i 到所有其他点的距离出现的频次
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < points.length; i++) {

            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    // 计算距离时不进行开根运算, 以保证精度
                    int dis = dis(points[i], points[j]);
                    if (record.containsKey(dis)) {
                        record.put(dis, record.get(dis) + 1);
                    } else {
                        record.put(dis, 1);
                    }
                }
            }
            for (Integer dis : record.keySet()) {
                res += record.get(dis) * (record.get(dis) - 1);
            }

            // 复用map
            record.clear();
        }
        return res;
    }

    private int dis(int[] pa, int[] pb) {
        return (pa[0] - pb[0]) * (pa[0] - pb[0]) +
                (pa[1] - pb[1]) * (pa[1] - pb[1]);
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {1, 0}, {2, 0}};
        System.out.println(new Solution1().numberOfBoomerangs(points));
    }
}