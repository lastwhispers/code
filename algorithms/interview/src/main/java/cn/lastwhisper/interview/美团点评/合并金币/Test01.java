package cn.lastwhisper.interview.美团点评.合并金币;


import java.util.Scanner;

public class Test01 {
    /**
     * 有 N 堆金币排成一排，第 i 堆中有 C[i]块金币。 每次合并都会将相邻的两堆金币合并为一堆，成本为这两堆金币块数之和。
     * 经过N-1次合并，最终将所有金币合并为一堆。请找出将金币合并为一堆的最低成本。
     *
     * 比如，n=3时表示共有3堆金币，每堆重量分别是2、1、9.
     *  一种合并方案是2和9合并，新堆重量是11，消耗体力为11；
     *  接着11和1合并，新堆重量是12，消耗体力为12，因此总消耗体力是11+12=23。
     *  另一种方案是：1和2合并，新堆重量是3，消耗体力为3；
     *  接着3和9合并，新堆重量是12，消耗体力为12，因此总消耗体力是3+12=15。可以证明这就是最少消耗体力。
     *
     * 其中，1 <= N <= 30，1 <= C[i] <= 100
     *
     * f[i,j]=min{f[i,k]+f[k+1,j]+sum[i,j]|i<=k<j}
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] gold = new int[n];
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            gold[i] = in.nextInt();
            if (i == 0) sum[i] = gold[i];
            else sum[i] = sum[i - 1] + gold[i];
        }

        in.close();
        long temp;
        long min;
        long[][] dp = new long[n][n];
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n && i + l < n; i++) {
                min = dp[i][i] + dp[i + 1][i + l];
                for (int k = i + 1; k <= i + l - 1; k++) {
                    temp = dp[i][k] + dp[k + 1][i + l];
                    if (temp < min) min = temp;
                }
                if (i > 0)
                    dp[i][i + l] = min + sum[i + l] - sum[i - 1];
                else dp[i][i + l] = min + sum[l];
            }
        }
        System.out.println(dp[0][n - 1]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}