package cn.lastwhisper.search;

import cn.lastwhisper.ArrayUtil;

/**
 *
 * @author lastwhisper
 */
public class InsertValueSearch {
    private static int count = 0;
    public static void main(String[] args) {
        int[] arr = ArrayUtil.generateArrByOrder(100);
        //int[] arr = {1, 1, 1, 1, 10, 89, 1000, 1234};
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 37));
        System.out.println("查找次数："+count);
    }

    /**
     * 插值查找（递归）
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return int
     */
    public static int insertValueSearch(int[] arr, int left, int right, int target) {
        count++;
        // 必要条件
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        // 自适应写法
        int mid = left + (right - left) * (target - arr[left]) / (arr[right] - arr[left]);
        if (target > arr[mid]) {
            return insertValueSearch(arr, mid + 1, right, target);
        } else if (target < arr[mid]) {
            return insertValueSearch(arr, left, mid - 1, target);
        } else {
            return mid;
        }
    }
}
