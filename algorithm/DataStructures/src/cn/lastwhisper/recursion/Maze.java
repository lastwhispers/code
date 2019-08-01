package cn.lastwhisper.recursion;

/**
 * 递归解决迷宫问题
 * @author lastwhisper
 */
public class Maze {
    public static void main(String[] args) {
        // 1、初始化迷宫 8(row)7(column)
        int map[][] = new int[8][7];
        // map.length——》row.length，map[0].length——》 column.length
        // 左右边界设为1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        //  上下边界置为1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        // 设置迷宫
        map[3][1] = 1;
        map[3][2] = 1;

        //map[3][1] = 1;
        //map[3][2] = 1;
        //map[4][3] = 1;
        //map[3][4] = 1;
        //map[2][4] = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("=============走出迷宫=============");
        getWay(map, 1, 1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
    }

    /**
     *  map表示地图、i，j表示从地图的某个位置开始出发、map[6][5]为终点
     *   约定：0表示没有走过、1表示不通、2表示通路、3表示已经走过无法走通
     *   走迷宫策略：下——》右——》上——》左，如果该点走不通再递归回溯
     * @return boolean
     */
    public static boolean getWay(int[][] map, int i, int j) {
        // 找到终点
        if (map[6][5] == 2) {
            return true;
        } else {
            // 0表示没有走过、1表示不通、2表示通路、3表示已经走过无法走通
            if (map[i][j] == 0) {
                // 尝试 下——》右——》上——》左
                map[i][j] = 2;
                if (getWay(map, i + 1, j)) {
                    //下
                    return true;
                } else if (getWay(map, i, j + 1)) {
                    //右
                    return true;
                } else if (getWay(map, i - 1, j)) {
                    //上
                    return true;
                } else if (getWay(map, i, j - 1)) {
                    //左
                    return true;
                } else {
                    // 下——》右——》上——》左走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 此路不通或已走过
                return false;
            }
        }
    }


}
