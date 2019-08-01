package cn.lastwhisper.sparsematrix;

/**
 * @author lastwhisper
 */
public class BestSpmatrix {
    /**
     * 三元组，保存有效元素的信息
     */
    private static class Node {
        int i, j; // 三元组的行、列坐标
        int v; // 三元组的值

        public Node(int i, int j, int v) {
            this.i = i;
            this.j = j;
            this.v = v;
        }
    }

    private static class Spmatrix {
        int rows, cols;//稀疏矩阵的行、列数
        int terms;   //稀疏矩阵的非零元素个数
        Node[] node;//存放稀疏矩阵的三元组表

        public Spmatrix(int rows, int cols, int terms, Node[] node) {
            this.rows = rows;
            this.cols = cols;
            this.terms = terms;
            this.node = node;
        }
    }

    public static void main(String[] args) {
        // 准备一个稀疏矩阵
        int rowNum = 11;
        int columnNum = 11;
        int[][] chessMatrix = new int[rowNum][columnNum];
        chessMatrix[1][2] = 1;
        chessMatrix[2][3] = 2;

        // 压缩
        int sum = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (chessMatrix[i][j] != 0) {
                    sum++;
                }
            }
        }
        Spmatrix spmatrix = new Spmatrix(rowNum, columnNum, sum, new Node[sum]);

        int index = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (chessMatrix[i][j] != 0) {
                    spmatrix.node[index++] = new Node(i, j, chessMatrix[i][j]);
                }
            }
        }

        for (int i = 0; i < spmatrix.node.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", spmatrix.node[i].i, spmatrix.node[i].j, spmatrix.node[i].v);
        }

        // 还原
        // 取出第一行元素的行、列维度，创建稀疏矩阵
        int[][] matrix = new int[spmatrix.rows][spmatrix.cols];
        // 追个取出剩余行并还原稀疏矩阵
        for (int i = 0; i < spmatrix.node.length; i++) {
            matrix[spmatrix.node[i].i][spmatrix.node[i].j] = spmatrix.node[i].v;
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}
