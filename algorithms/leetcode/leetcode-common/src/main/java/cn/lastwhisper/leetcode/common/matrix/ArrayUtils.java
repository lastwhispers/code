package cn.lastwhisper.leetcode.common.matrix;

/**
 *
 * @author lastwhisper
 * @date 2020/2/4
 */
public class ArrayUtils {

    /**
     * 创建二维字符数组
     */
    public static char[][] createCharArrays(String[] arr) {
        int m = arr.length, n = arr[0].length();
        char[][] chars = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                chars[i][j] = arr[i].charAt(j);
            }
        }
        return chars;
    }

    /**
     * 创建二维数组
     */
    public static int[][] createIntArrays(String[] arr) {
        int m = arr.length, n = arr[0].length();
        int[][] ints = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ints[i][j] = arr[i].charAt(j) - '0';
            }
        }
        return ints;
    }

}
