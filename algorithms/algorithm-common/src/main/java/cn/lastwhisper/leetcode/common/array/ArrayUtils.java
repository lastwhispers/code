package cn.lastwhisper.leetcode.common.array;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lastwhisper
 * @date 2020/2/4
 */
public class ArrayUtils {

    /**
     * {"ab","cd"}=>
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
     * {{"a","b","c","d"}}=>
     * 创建二维字符数组
     */
    public static char[][] createCharArrays(String[][] arr) {
        int m = arr.length, n = arr[0].length;
        char[][] chars = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                chars[i][j] = arr[i][j].charAt(0);
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


    /**
     * 创建list
     */
    public static List<List<Integer>> createList(int[][] arrays) {
        List<List<Integer>> lists = new ArrayList<>(arrays.length);
        for (int[] array : arrays) {
            List<Integer> list = new ArrayList<>(array.length);
            for (int num : array) {
                list.add(num);
            }
            lists.add(list);
        }
        return lists;
    }


    /**
     * 生成无序数组
     * @param length 生成数组的长度
     * @param region 生成数字的范围
     */
    public static int[] generateArrByRandom(int length, int region) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * region); // region=8000000 生成一个[0,8000000) 的一个数
        }
        return arr;
    }

    /**
     * 生成n~0倒序数组
     */
    public static int[] generateArrByOrder(int n) {
        int[] arr = new int[n];
        for (int i = n - 1; i > 0; i--) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
