package cn.lastwhisper.leetcode.week.one180.矩阵中的幸运数_5356_简单;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    public List<Integer> luckyNumbers(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for (int[] rows : matrix) {
            int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                map.put(rows[j], j);
                min = Math.min(min, rows[j]);
            }
            int min_col = map.get(min);
            map.clear();
            for (int[] row : matrix) {
                max = Math.max(row[min_col], max);
            }
            if (max == min) {
                ans.add(max);
            }
        }
        return ans;
    }
}
