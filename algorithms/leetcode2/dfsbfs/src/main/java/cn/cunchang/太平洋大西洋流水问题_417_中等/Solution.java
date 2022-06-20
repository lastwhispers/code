package cn.cunchang.太平洋大西洋流水问题_417_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] enableAo = new int[m][n];//太平洋的节点记录矩阵
        int[][] enablePa = new int[m][n];//大西洋的节点记录矩阵
        //1. 从上下边界开始两大洋的回流搜索，变动的是列
        for (int i = 0; i < n; i++) {
            // 最上面一列，dfs太平洋
            dfs(heights, enablePa, 0, i);
            // 最下面一列，dfs大西洋
            dfs(heights, enableAo, m - 1, i);
        }
        //2. 从左右边界开始两大洋的回流搜索，变动的是行
        for (int i = 0; i < m; i++) {
            // 最左面一行，dfs太平洋
            dfs(heights, enablePa, i, 0);
            // 最右面一行，dfs大西洋
            dfs(heights, enableAo, i, n - 1);
        }
        //3. 输出交叠的坐标
        List<List<Integer>> cnt = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (enableAo[i][j] == 1 && enablePa[i][j] == 1) {
                    cnt.add(Arrays.asList(i, j));
                }
            }
        }
        return cnt;
    }

    public static void dfs(int[][] heights, int[][] tmp, int curI, int curJ) {
        //标记可以从海洋流回经过的节点
        tmp[curI][curJ] = 1;
        //开始深度优先搜索当前坐标的4个方向
        //1. 设置更新的坐标
        int[] di = new int[]{1, -1, 0, 0};//上下移动
        int[] dj = new int[]{0, 0, 1, -1};//左右移动
        //2. 更新坐标并递归搜索
        for (int index = 0; index < 4; index++) {
            int newI = curI + di[index];
            int newJ = curJ + dj[index];
            //判断下标是否越界
            if (newI < 0 || newJ < 0 || newI >= heights.length || newJ >= heights[0].length) {
                continue;
            }
            if (heights[curI][curJ] <= heights[newI][newJ] && tmp[newI][newJ] != 1) {
                dfs(heights, tmp, newI, newJ);
            }
        }
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        System.out.println(new Solution().pacificAtlantic(heights));
    }

}
