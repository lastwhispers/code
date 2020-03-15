package cn.lastwhisper.leetcode.recurionbacktracking.岛屿数量_200_中等;

class Solution1 {
    private static final int[][] directory = new int[][]{
            {-1, 0},// up
            {0, 1},// right
            {1, 0},// down
            {0, -1},// left
    };
    /**
     * 题目地址：https://leetcode-cn.com/problems/number-of-islands/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯
     *     '0'（水）、'1'（陆地）
     *  递归：标记陆地
     *  回溯：无
     *  递归终止：没有未被访问的陆地
     * -------------------------------------------------------------------
     * 时间复杂度：O(n*m)
     * 空间复杂度：O(n*m)
     */
    private int m, n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 找到岛屿
                if (grid[i][j] == '1' && !visited[i][j]) {
                    result++;
                    // 对第一个陆地以及通过第一个陆地能到达的陆地进行dfs标记
                    dfsMark(grid, i, j, visited);
                }
            }
        }
        return result;
    }

    /**
     * dfs遍历，对遍历的位置进行标记
     * @param grid  地图
     * @param startX 当前x坐标
     * @param startY 当前y坐标
     * @param visited 地图所到标识
     */
    private void dfsMark(char[][] grid, int startX, int startY, boolean[][] visited) {
        visited[startX][startY] = true;
        for (int i = 0; i < directory.length; i++) {
            int newX = startX + directory[i][0];
            int newY = startY + directory[i][1];
            if (isArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                dfsMark(grid, newX, newY, visited);
            }
        }
        // 不需要回溯重置状态，因为该方法的作用就是对遍历到的岛屿进行标记
        //visited[startX][startY] = false;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }


    public static void main(String[] args) {
        //char[][] grid = {
        //        {'1', '1', '1', '1', '0'},
        //        {'1', '1', '0', '1', '0'},
        //        {'1', '1', '0', '0', '0'},
        //        {'0', '0', '0', '0', '0'},
        //};

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        System.out.println(new Solution1().numIslands(grid));
    }
}  