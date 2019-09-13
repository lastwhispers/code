package cn.lastwhisper.offer.interview_4;

/**
 * 牛客提交版本
 */
public class Solution {
    public boolean Find(int target, int[][] array) {
        boolean flag = false;
        if (array == null || array.length == 0 || array[0].length == 0) {
            return flag;
        }

        // 矩阵行数和列数
        int rows = array.length, columns = array[0].length;
        // 目标值比数组最大值还大
        if (array[rows - 1][columns - 1] < target) {
            return flag;
        }
        // 从矩阵右上角进行剔除列和行
        int row = 0, column = columns - 1;
        while (row <= rows && column >= 0) {
            if (array[row][column] == target) {
                //找到了
                flag = true;
                break;
            } else if (array[row][column] > target) {
                //右上角列上最小值大于target，剔除列
                column--;
            } else {
                //target大于右上角列上最小值，剔除行
                row++;
            }
        }
        return flag;
    }
}