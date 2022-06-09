package cn.cunchang.无重叠区间_435_中等;

import org.junit.Assert;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /**
     * 思考：
     * 1. 什么是“区间不重叠”？intervals[i][1]<=intervals[i+1][0]，说明区间i和区间i+1不重叠，否则就是重叠
     * 2. 什么是“移除区间的最小数量，使剩余区间互不重叠”？比如“[[1,100],[11,22],[1,11],[2,12]]”移除后三个区间也不重叠，但是最小数量是移除1、4区间。
     * -------------------------------------------------------------------
     * 思路：
     * 1、按照区间结尾升序
     * 2、相邻单位区间，重合就移除；不重叠继续判断
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[1] - interval2[1];
            }
        });

        int total = 0, pre = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pre) {
                pre = intervals[i][1];
            } else {
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] g = new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        Assert.assertEquals(2, new Solution().eraseOverlapIntervals(g));

    }
}