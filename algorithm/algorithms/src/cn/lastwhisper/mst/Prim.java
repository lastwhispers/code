package cn.lastwhisper.mst;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class Prim {


    public static void main(String[] args) {
        // 图顶点的数据
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        //邻接矩阵的关系使用二维数组表示，10000表示两个点不联通（Integer.MAX_VALUE）
        int[][] matrix = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        //创建MGraph对象
        MGraph graph = new MGraph(vertexs);
        // 最小生成树
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertexs, data, matrix);
        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }

}

// 最小生成树
class MinTree {

    /**
     *
     * @param graph 图
     * @param vertexs 顶点个数
     * @param data  顶点数据
     * @param matrix 顶点的关联映射的邻接矩阵
     * @return void
     */
    public void createGraph(MGraph graph, int vertexs, char[] data, int[][] matrix) {
        // 初始化顶点数据
        for (int i = 0; i < vertexs; i++) {
            graph.data[i] = data[i];
            // 初始化邻接矩阵
            for (int j = 0; j < vertexs; j++) {
                graph.matrix[i][j] = matrix[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 根据图求得最小生成树
     * @param graph 图对象
     * @param v 起始顶点
     * @return void
     */
    public void prim(MGraph graph, int v) {
        // 记录已经访问过的顶点，true为访问过，false为没有访问过
        boolean[] visited = new boolean[graph.vertexs];
        visited[v] = true;
        int index1 = -1, index2 = -1;
        int minWeight = 10000;
        // n个顶点会产生n-1条边
        for (int k = 1; k < graph.vertexs; k++) {
            // 以i为顶点，向周围顶点访问，找到权值最小的连通顶点
            for (int i = 0; i < graph.vertexs; i++) {
                for (int j = 0; j < graph.vertexs; j++) {
                    if (visited[i] && !visited[j] && graph.matrix[i][j] < minWeight) {
                        minWeight = graph.matrix[i][j];
                        index1 = i;
                        index2 = j;
                    }
                }
            }
            System.out.printf("边<%s,%s> 权值：%d\n", graph.data[index1], graph.data[index2], minWeight);
            minWeight = 10000;
            visited[index2] = true;
        }
    }
}

// 图
class MGraph {
    // 顶点个数
    int vertexs;
    // 顶点数据
    char[] data;
    // 邻接矩阵
    int[][] matrix;

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        matrix = new int[vertexs][vertexs];
    }
}
