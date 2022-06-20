package cn.cunchang.太平洋大西洋流水问题_417_中等;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_my {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        // 对每个单元格搜索，判断是否可以向上或左同时向下或右，可以就说明满足
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights.length; j++) {
                boolean flag = pacificAtlantic(heights, i, j);
                if (flag) {
                    result.add(Arrays.asList(i,j));
                }
            }
        }
        return result;
    }

    /**
     * @param i 纵
     * @param j 横
     */
    private boolean pacificAtlantic(int[][] heights, int i, int j) {

        boolean pacificEnable1 = true;
        // 上；i减减
        for (int k = i; k >= 0; k--) {
            if (heights[i][j] < heights[k][j]) {
                pacificEnable1 = false;
                break;
            }
            if (i - k > 1 && heights[i][j] == heights[k][j]) {
                pacificEnable1 = false;
                break;
            }
        }
        boolean pacificEnable2 = true;
        // 左 j减
        for (int k = j; k >= 0; k--) {
            if (heights[i][j] < heights[i][k]) {
                pacificEnable2 = false;
                break;
            }
            if (j - k > 1 && heights[i][j] == heights[k][j]) {
                pacificEnable1 = false;
                break;
            }
        }

        boolean atlanticEnable1 = true;
        // 下
        for (int k = i; k < heights.length; k++) {
            if (heights[i][j] < heights[k][j]) {
                atlanticEnable1 = false;
                break;
            }
            if (k - i > 1 && heights[i][j] == heights[k][j]) {
                pacificEnable1 = false;
                break;
            }
        }
        boolean atlanticEnable2 = true;
        // 右
        for (int k = j; k < heights.length; k++) {
            if (heights[i][j] < heights[i][k]) {
                atlanticEnable2 = false;
                break;
            }
            if (k - j > 1 && heights[i][j] == heights[k][j]) {
                pacificEnable1 = false;
                break;
            }
        }

        return (pacificEnable1 || pacificEnable2) && (atlanticEnable1 || atlanticEnable2);
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        System.out.println(new Solution_my().pacificAtlantic(heights));
    }
}