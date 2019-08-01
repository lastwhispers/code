package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

/**
 * @author lastwhisper
 */
public class DubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 9, -1, 10, -2};
        //int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] arr = ArrayUtil.generateArrByRandom(100000);
        // 计算耗时
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");
    }

    /**
     * 冒泡排序O(n^2)
     *
     * @param arr
     * @return void
     */
    public static void bubbleSort(int[] arr) {
        int temp;
        // 标识是否发生交换，默认不交换;即默认数组是升序
        boolean flag = false;
        // 外循环是当前已经找到最大值得个数
        for (int i = 0; i < arr.length; i++) {
            // 内循环进行冒泡，每循环一次arr最后i位数就是最大的数，可以不进行比较，即arr.length-i。
            // 10个数两两相邻比较，需要比较9次，即arr.length - 1
            // 所以内循环为：arr.length - 1 - i
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.printf("第%d趟排序后的数组", i + 1);
            //System.out.println(Arrays.toString(arr));
            // 内循环未发生交换，说明数组目前已经有序，直接退出外循环
            if (!flag) {
                break;
            } else {
                flag = false;//重置
            }
        }
    }
}
