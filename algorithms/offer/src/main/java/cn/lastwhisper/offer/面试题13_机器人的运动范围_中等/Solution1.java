package cn.lastwhisper.offer.面试题13_机器人的运动范围_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯 DFS
     * -------------------------------------------------------------------
     * 时间复杂度：
     * 空间复杂度：
     */

    private static final int[][] directory = new int[][]{
            {-1, 0},// up
            {0, 1},// right
            {1, 0},// down
            {0, -1},// left
    };

    private int m, n, k;

    private boolean isValid(int x, int y, int k) {
        return x >= 0 && y >= 0 && x < m && y < n && digitalSum(x) + digitalSum(y) <= k;
    }

    private int digitalSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    private int findCount(int startX, int startY, boolean[][] visited) {
        int result = 0;

        visited[startX][startY] = true;
        for (int i = 0; i < directory.length; i++) {
            int newX = startX + directory[i][0];
            int newY = startY + directory[i][1];
            // 接下来要访问的坐标合法，切未访问过
            if (isValid(newX, newY, k) && !visited[newX][newY]) {
                result += findCount(newX, newY, visited);
            }
        }

        return result + 1;
    }

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        return findCount(0, 0, new boolean[m][n]);
    }


    public static void main(String[] args) {
        //int m = 2, n = 3, k = 1;
        //int m = 3, n = 1, k = 0;
        int m = 35, n = 38, k = 18;
        System.err.println(new Solution1().movingCount(m, n, k));
    }
}