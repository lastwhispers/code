package cn.cunchang.od;


import java.util.Scanner;

public class Main3 {

    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // 邻接矩阵
        int[][] arrs = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arrs[i][j] = sc.nextInt();
            }
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    int subResult = helper(arrs, visited, i, j);
                    res = Math.max(subResult, res);
                }
            }
        }
        System.out.println(res);
    }

    private static int helper(int[][] arrs, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }
        if (visited[i][j] || arrs[i][j] != 1) {
            return 0;
        }
        visited[i][j] = true;
        int subRes = helper(arrs, visited, i + 1, j) //下
                + helper(arrs, visited, i - 1, j) // 上
                + helper(arrs, visited, i, j - 1) // 左
                + helper(arrs, visited, i, j + 1); //右
        return subRes + 1;
    }


//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        // 邻接矩阵
//        int[][] arrs = new int[n][m];
//        boolean[] visited = new boolean[n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                arrs[i][j] = sc.nextInt();
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            if (!visited[i]) {
//                helper(arrs, visited, i);
//                res++;
//            }
//        }
//        System.out.println(res);
//    }
//
//    public static void helper(int[][] arrs, boolean[] visited, int idx) {
//        if (idx >= arrs.length) {
//            return;
//        }
//        if (visited[idx]) {
//            return;
//        }
//        visited[idx] = true;
//        for (int i = 0; i < idx; i++) {
//            helper(arrs, visited, i);
//        }
//    }

}