
package cn.cunchang.省份数量_547_中等;

class Solution {
    /**
     * 有几个需要注意的条件
     * isConnected[i][i] == 1
     * isConnected[i][j] == isConnected[j][i]
     * <p>
     * isConnected[i][i] == 1 说明是个无向图
     */
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];

        int res = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                findCircleNum(isConnected, i, visited);
                res++;
            }
        }
        return res;
    }
    // 深度遍历，相邻节点，并对访问过的节点进行标记
    private void findCircleNum(int[][] isConnected, int i, boolean[] visited) {
        visited[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                findCircleNum(isConnected, j, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};
        System.out.println(new Solution().findCircleNum(isConnected));
    }
}