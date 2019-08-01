package cn.lastwhisper.sparsematrix;

/**
 * 稀疏矩阵压缩与还原
 * @author lastwhisper
 */
public class SparseMatrix {


    /**
     *  压缩稀疏矩阵
     *  稀疏数组第一行保存数组的行、列的维度以及有效元素的个数
     *  其余行保存有效数据行、列的坐标以及有效数据本身
     *
     * @param sparseMatrix 稀疏矩阵
     * @param rows 行维度
     * @param cols 列维度
     * @return int[][]
     */
    private int[][] compress(int[][] sparseMatrix, int rows, int cols) {
        // 计算有效元素的个数
        int sum = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sparseMatrix[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 三元组表
        int[][] tripleTable = new int[sum + 1][3];
        tripleTable[0][0] = rows;
        tripleTable[0][1] = cols;
        tripleTable[0][2] = sum;
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (sparseMatrix[i][j] != 0) {
                    index++;
                    tripleTable[index][0] = i;
                    tripleTable[index][1] = j;
                    tripleTable[index][2] = sparseMatrix[i][j];
                }
            }
        }
        return tripleTable;
    }

    /**
     * 还原稀疏矩阵
     *
     * @param tripleTable
     * @return int[][]
     */
    private int[][] recovery(int[][] tripleTable) {
        // 取出第一行元素的行、列维度，创建稀疏矩阵
        int[][] matrix = new int[tripleTable[0][0]][tripleTable[0][1]];
        // 追个取出剩余行并还原稀疏矩阵
        for (int i = 1; i < tripleTable.length; i++) {
            matrix[tripleTable[i][0]][tripleTable[i][1]] = tripleTable[i][2];
        }
        return matrix;
    }

    public static void main(String[] args) {
        // 准备一个稀疏矩阵
        int rowNum = 11;
        int columnNum = 11;
        int[][] chessMatrix = new int[rowNum][columnNum];
        chessMatrix[1][2] = 1;
        chessMatrix[2][3] = 2;
        // 压缩
        SparseMatrix sparseMatrix = new SparseMatrix();
        int[][] tripleTable = sparseMatrix.compress(chessMatrix, rowNum, columnNum);

        for (int i = 0; i < tripleTable.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", tripleTable[i][0], tripleTable[i][1], tripleTable[i][2]);
        }
        // 还原
        chessMatrix = sparseMatrix.recovery(tripleTable);

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                System.out.printf("%d\t", chessMatrix[i][j]);
            }
            System.out.println();
        }

    }
}
