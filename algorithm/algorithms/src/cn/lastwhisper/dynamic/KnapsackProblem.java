package cn.lastwhisper.dynamic;

/**
 * 背包问题
 * @author lastwhisper
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] weight = {1, 4, 3};
        //物品的价值
        int[] value = {1500, 3000, 2000};
        //背包的容量
        int capacity = 4;
        // 物品的个数
        int n = value.length;

        // v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        // v[i][j]的有效值是从下标1开始
        int[][] v = new int[n + 1][capacity + 1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n + 1][capacity + 1];
        // 初始化数组
        // 第一列全为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        // 第一行全为0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        // i表示当前物品 吉他、音响、电脑
        for (int i = 1; i < v.length; i++) {
            // j表示当前背包容量 1,2,3,4...
            for (int j = 1; j < v[0].length; j++) {
                // 当前物品容量大于当前背包容量，使用上一个单元格的装入策略
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 当前物品容量小于当前背包容量时，说明可以放的下物品，
                    // 需要比较“上一个物品(i - 1)容量(j)时的最大价值的装入策略”与
                    // “当前物品的价值+上一个物品(i - 1)容量(j-weight[i],其中weight[i]是当前物品重量)时的最大价值的装入策略”
                    //v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);

                    // 为了记录商品存入背包的情况，不能直接使用上面的写法，需要改写为if-else
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        System.out.println("最大价值："+v[n][capacity]);
        // 行
        int i = path.length - 1;
        // 列
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= weight[i - 1];
            }
            i--;
        }


    }
}
