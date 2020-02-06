package cn.lastwhisper.leetcode.recurionbacktracking.单词搜索_79_中等;

class Solution1 {
    /**
     * 题目地址：https://leetcode-cn.com/problems/word-search/
     * -------------------------------------------------------------------
     * 思考：
     * -------------------------------------------------------------------
     * 思路：递归+回溯
     *  递归：在board起始位置上下左右去搜索，每一个位置保证：
     *      1.与该index单词字母相同
     *      2.数组不越界
     *      3.未被访问过
     *      4.index+1也满足这1、2、3、4条件
     *  回溯：将当前位置标记为未访问
     *  递归终止：index等于单词长度-1，最后一个字母相同
     * -------------------------------------------------------------------
     * 时间复杂度：O(m*n*m*n)
     * 空间复杂度：O(m*n)
     */
    private int m, n;
    private static final int[][] directory = new int[][]{
            {-1, 0},// up
            {0, 1},// right
            {1, 0},// down
            {0, -1},// left
    };

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null)
            throw new IllegalArgumentException("board or word can not be null!");
        m = board.length;
        if (m == 0)
            throw new IllegalArgumentException("board can not be empty.");
        n = board[0].length;
        if (n == 0)
            throw new IllegalArgumentException("board can not be empty.");
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, visited, i, j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean searchWord(char[][] board, String word, int index, boolean[][] visited, int startX, int startY) {
        if (index == word.length() - 1) {
            return board[startX][startY] == word.charAt(index);
        }

        if (board[startX][startY] == word.charAt(index)) {
            visited[startX][startY] = true;
            for (int i = 0; i < directory.length; i++) {
                int newX = startX + directory[i][0];
                int newY = startY + directory[i][1];
                if (isArea(newX, newY) && !visited[newX][newY]) {
                    if (searchWord(board, word, index + 1, visited, newX, newY)) {
                        return true;
                    }
                }
            }
            visited[startX][startY] = false;
        }

        return false;
    }


    private boolean isArea(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }


    public static void main(String[] args) {
        char[][] board =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };
        //String word = "ABCCED";
        //String word = "SEE";
        String word = "ABCB";
        System.out.println(new Solution1().exist(board, word));
    }

}