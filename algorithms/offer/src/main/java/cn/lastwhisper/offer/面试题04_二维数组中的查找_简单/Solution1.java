package cn.lastwhisper.offer.面试题04_二维数组中的查找_简单;

class Solution1 {
    /**
     * 右上角列上最小值与目标值比较
     *  大于时,说明整个列都比目标值大,剔除该列,缩小范围
     *  小于时,说明该列可能有目标值,剔除该行,缩小范围
     *  等于时,找到该值
     *
     * @param matrix 规则矩阵
     * @param target 目标值
     * @return boolean
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean flag = false;
        // 数据校验：防止空数组
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // 矩阵行数和列数
        int rows = matrix.length - 1, columns = matrix[0].length - 1;
        // 目标值比数组最大值还大
        if (matrix[rows][columns] < target) {
            return false;
        }
        // 从矩阵右上角进行剔除列和行
        int row = 0, column = columns;
        while (row <= rows && column >= 0) {
            if (matrix[row][column] == target) {
                //找到了
                flag = true;
                break;
            } else if (matrix[row][column] > target) {
                //右上角列上最小值大于target，剔除列
                column--;
            } else {
                //target大于右上角列上最小值，剔除行
                row++;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        //int[][] array = {
        //        {1, 2, 8, 9},
        //        {2, 4, 9, 12},
        //        {4, 7, 10, 13},
        //        {6, 8, 11, 15}
        //};
        int[][] array = {
                {-5}
        };
        System.out.println(new Solution1().findNumberIn2DArray(array, -5));
    }
}