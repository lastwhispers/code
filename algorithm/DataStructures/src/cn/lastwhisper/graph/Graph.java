package cn.lastwhisper.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 图
 * @author lastwhisper
 */
public class Graph {
    private List<String> vertexList; // 存储顶点集合
    private int[][] matrix; // 存储图对应的邻接矩阵
    private int numOfEdges; // 记录边的数量
    private boolean[] isVisited; // 记录某结点是否被访问

    // 初始化图的参数
    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        matrix = new int[n][n];
        //isVisited = new boolean[n];
        numOfEdges = 0;
    }

    public static void main(String[] args) {
        // 顶点
        //String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(vertexs.length);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        //graph.addEdge(0, 1, 1); // A-B
        //graph.addEdge(0, 2, 1); //
        //graph.addEdge(1, 2, 1); //
        //graph.addEdge(1, 3, 1); //
        //graph.addEdge(1, 4, 1); //
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(3, 7, 1);
        graph.addEdge(4, 7, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 1);
        graph.addEdge(5, 6, 1);
        //graph.show();
        System.out.println("深度优先：");
        graph.dfs();
        System.out.println("广度优先：");
        graph.bfs();
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
        System.out.println();
    }

    // 广度优先
    public void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列头结点对应下标
        int w; // 邻接结点w
        // 记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + "=>");
        // 标记结点已经访问
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 出队，遍历当前出队结点的所有邻接结点，并放入队列中
            u = queue.removeFirst();
            // 获取u的第一个邻接结点下标
            w = getFristNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    // 标记结点已经访问
                    isVisited[w] = true;
                    //入队，用于下一次出队
                    queue.addLast(w);
                }
                // 从队列头结点，找到邻接结点w之后的邻接结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                // 0,1,2,3,4,5
                dfs(isVisited, 0);
            }
        }
        System.out.println();
    }

    // 深度优先遍历算法
    public void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该结点,输出
        System.out.print(getValueByIndex(i) + "->");
        // 将结点设置为已经访问
        isVisited[i] = true;
        // 查找结点i的第一个邻接结点j
        int w = getFristNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 第i个结点下标i，matrix的row；w是邻接结点下标，matrix的column
            w = getNextNeighbor(i, w);
        }
    }

    /**
     *  根据前一个邻接结点的下标来获取下一个邻接结点
     *
     *       0  1  2  3  4
     *       A  B  C  D  E
     *   0 A[0, 1, 1, 0, 0]
     *   1 B[1, 0, 1, 1, 1]
     *   2 C[1, 1, 0, 0, 0]
     *   3 D[0, 1, 0, 0, 0]
     *   4 E[0, 1, 0, 0, 0]
     *   v1=1,v2=1，下一个邻接结点下标为1
     * @param v1 当前weight横下标
     * @param v2 当前weight纵下标
     * @return int
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < getNumOfVertex(); i++) {
            if (matrix[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到某一个结点的第一个邻接结点的下标
     *  例如：
     *       0  1  2  3  4
     *       A  B  C  D  E
     *   0 A[0, 1, 1, 0, 0]
     *   1 B[1, 0, 1, 1, 1]
     *   2 C[1, 1, 0, 0, 0]
     *   3 D[0, 1, 0, 0, 0]
     *   4 E[0, 1, 0, 0, 0]
     *  index=0，第一个结点A的第一个邻接结点就是B，第一个邻接结点的下标就是1
     * @param index 某一个结点
     * @return int
     */
    public int getFristNeighbor(int index) {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (matrix[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 返回结点下标对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 添加结点
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1 结点1的下标
     * @param v2 结点2的下标
     * @param weight 1表示有通路，0表示无通路
     */
    public void addEdge(int v1, int v2, int weight) {
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight;
        numOfEdges++;
    }

    // 显示图对应矩阵
    public void show() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return matrix[v1][v2];
    }
}
