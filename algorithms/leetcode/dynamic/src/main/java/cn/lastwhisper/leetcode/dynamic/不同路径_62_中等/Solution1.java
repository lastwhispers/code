package cn.lastwhisper.leetcode.dynamic.不同路径_62_中等;

class Solution1 {

    /**
     * https://leetcode-cn.com/problems/unique-paths/%20/
     * 核心思路：
     *  1.递归；
     *  2.递归展开；
     *  3.机器人一定会走m+n-2步，即从m+n-2中挑出m-1步向下走不就行了
     *      C((m+n-2),(m-1))
     * -------------------------------------------------------------------
     */
    public int uniquePaths(int m, int n) {
        //if (m == 1 || n == 1) {
        //    return 1;
        //}
        //return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);


        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++)
            result[i][0] = 1;
        for (int j = 0; j < n; j++)
            result[0][j] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                result[i][j] = result[i - 1][j] + result[i][j - 1];
            }
        }
        return result[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m = 7, n = 3;
        System.out.println(new Solution1().uniquePaths(m, n));
    }
}