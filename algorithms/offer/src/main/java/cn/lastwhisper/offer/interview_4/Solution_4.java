package cn.lastwhisper.offer.interview_4;

/**
 * 面试题4：二维数组中的查找
 *  从左到右、从上到下递增
 * @author cn.lastwhisper
 */
public class Solution_4 {
    public static void main(String[] args) {
        int[][] array1 = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        int[][] array2 = {{}};
        // 查询不存在的值
        //System.out.println(new Solution_4().Find(16, array1));
        // 查询空数组
        System.out.println(new Solution_4().Find(16, array2));

        //System.out.println(new Solution_4().Find2(10, array1));
    }

    /**
     * 右上角列上最小值与目标值比较
     *  大于时,说明整个列都比目标值大,剔除该列,缩小范围
     *  小于时,说明该列可能有目标值,剔除该行,缩小范围
     *  等于时,找到该值
     *
     * @param target 目标值
     * @param array 规则矩阵
     * @return boolean
     */
    public boolean Find(int target, int[][] array) {
        boolean flag = false;
        // 数据校验：防止空数组
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

    public boolean Find2(int target, int[][] array) {
        boolean flag = false;
        if (array == null || array.length == 0) {
            System.out.println("矩阵未初始化");
            return flag;
        }
        // 矩阵行数和列数
        int rows = array.length, columns = array[0].length;
        // 从矩阵左下角进行剔除列和行
        int row = rows - 1, column = 0;
        while (column <= columns && row >= 0) {
            if (array[row][column] == target) {
                //找到了
                flag = true;
                break;
            } else if (array[row][column] > target) {
                // 左下角最大值比目标值大,目标值可能在该列,剔除行
                row--;
            } else {
                column++;
                // 左下角最大值比目标值小,剔除列
            }
        }
        return flag;
    }
}
