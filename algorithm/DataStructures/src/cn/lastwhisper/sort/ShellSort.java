package cn.lastwhisper.sort;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        //int[] arr = ArrayUtil.generateArrByRandom(100000);
        // 计算耗时
        long start = System.currentTimeMillis();
        //shellSort1(arr); // 10490 ms
        shellSort2(arr); // 10490 ms
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        System.out.printf("希尔排序（交换法），数组：%s\n", Arrays.toString(arr));
    }


    /**
     * 希尔排序（位移法）（插入排序）
     *
     * @param arr
     * @return void
     */
    public static void shellSort2(int[] arr) {
        // {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
        int gap = arr.length / 2;
        int j;
        int temp;
        while (gap > 0) {
            //gap=5,2,1,0
            // i=5——》{0,5}
            // i=6——》{1,6}
            // i=7——》{2,7}
            // i=8——》{3,8}
            // i=9——》{4,9}
            // 对每个数组进行插入排序；由于每个数组并不是真的存在的数组，而是一个大数组
            // 所以通过gap进行取值
            for (int i = gap; i < arr.length; i++) {
                j = i; // 缓存每个数组的起始下标
                temp = arr[i]; // 缓存每个数组的起始下标的数值
                if (temp < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 从前往后移动
                        arr[j] = arr[j - gap];
                        j = j - gap;
                    }
                    //
                    arr[j] = temp;
                }
            }
            gap = gap / 2;
        }
    }

    /**
     * 希尔排序（交换法）
     *
     * @param arr
     * @return void
     */
    public static void shellSort1(int[] arr) {
        // 第1轮 {8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
        // 增量 gap=length/2
        int gap = arr.length / 2;
        int temp;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                // 交换
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
            gap = gap / 2;
        }
    }

    /**
     * 交换法希尔排序
     *
     * @param arr
     * @return void
     */
    public static void shellSortStudy(int[] arr) {
        // 第1轮 [8, 9, 1, 7, 2, 3, 5, 4, 6, 0]
        // 增量 gap=length/2
        int temp;
        for (int i = 5; i < arr.length; i++) {
            // 交换
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j + 5];
                    arr[j + 5] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.printf("第1轮插入，数组：%s\n", Arrays.toString(arr));
        // 第二轮 [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
        for (int i = 2; i < arr.length; i++) {
            // 交换
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j + 2];
                    arr[j + 2] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.printf("第2轮插入，数组：%s\n", Arrays.toString(arr));
        // 第二轮 [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
        for (int i = 1; i < arr.length; i++) {
            // 交换
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.printf("第2轮插入，数组：%s\n", Arrays.toString(arr));
    }
}
