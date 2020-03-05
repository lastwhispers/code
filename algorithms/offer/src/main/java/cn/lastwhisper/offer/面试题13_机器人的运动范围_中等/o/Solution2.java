package cn.lastwhisper.offer.面试题13_机器人的运动范围_中等.o;

import java.util.LinkedList;
import java.util.Queue;

class Solution2 {

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{0, 0, 0, 0});
        while (queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if (i < 0 || i >= m || j < 0 || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res++;
            queue.add(new int[]{i + 1, j, digitalSum(i + 1), sj});
            queue.add(new int[]{i, j + 1, si, digitalSum(j + 1)});
        }
        return res;
    }

    private int digitalSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}