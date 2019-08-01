package cn.lastwhisper.sort;

import cn.lastwhisper.ArrayUtil;

import java.util.Arrays;

/**
 * @author lastwhisper
 */
public class RadixSort {

    public static void main(String[] args) {
        // 算法实现步骤
        //int[] arr = {53, 3, 542, 748, 14, 214};
        //int[] arr = {3, 205, 14};
        //radixSortStudy(arr);

        // 计算耗时
        // 占用内存 10000000 * 10 * 4 /1024 / 1024 /1024 = 0.3725290298461914‬
        int[] arr = ArrayUtil.generateArrByRandom(10000000);
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + " ms");

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序
     *
     * @param arr
     * @return void
     */
    public static void radixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        // 桶元素计数器，记录每个桶中存放了多少数据
        int[] bucketElementCount = new int[10];
        // 原arr下标
        int arrIndex;
        // 除数
        int divisor = 1;
        // 最大值
        int max = arr[0];
        // 由于基数排序次数由最大数值的位数决定，所以需要求最大值
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // 最大值得位数，即排序次数
        int maxLength = (max + "").length();
        for (int f = 0; f < maxLength; f++) {
            for (int i = 0; i < arr.length; i++) {
                // digitOfElement对应放在哪个bucket中
                int digitOfElement = arr[i] / divisor % 10;
                // 放在第digitOfElement个bucket的第bucketElementCount[digitOfElement]下标位置
                bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
                // 下次放在bucket的第bucketElementCount[digitOfElement]++下标位置
                bucketElementCount[digitOfElement]++;
            }

            arrIndex = 0;
            // 将桶中数据放回原数组
            for (int i = 0; i < bucketElementCount.length; i++) {
                // 对应桶中有数据
                if (bucketElementCount[i] != 0) {
                    for (int j = 0; j < bucketElementCount[i]; j++) {
                        arr[arrIndex++] = bucket[i][j];
                    }
                }
                // 将桶元素计数器清空
                bucketElementCount[i] = 0;
            }
            divisor *= 10;
        }

    }

    /**
     * 桶排序算法实现步骤
     *
     * @param arr
     */
    public static void radixSortStudy(int[] arr) {
        //
        int[][] bucket = new int[10][arr.length];
        // 桶元素计数器，记录每个桶中存放了多少数据
        int[] bucketElementCount = new int[10];
        // 原arr下标
        int arrIndex;

        for (int i = 0; i < arr.length; i++) {
            // digitOfElement对应放在哪个bucket中
            int digitOfElement = arr[i] % 10;
            // 放在第digitOfElement个bucket的第bucketElementCount[digitOfElement]下标位置
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
            // 下次放在bucket的第bucketElementCount[digitOfElement]++下标位置
            bucketElementCount[digitOfElement]++;
        }

        arrIndex = 0;
        // 将桶中数据放回原数组
        for (int i = 0; i < bucketElementCount.length; i++) {
            // 对应桶中有数据
            if (bucketElementCount[i] != 0) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[arrIndex++] = bucket[i][j];
                }
            }
            // 将桶元素计数器清空
            bucketElementCount[i] = 0;
        }

        System.out.println("第1轮，对个位的排序处理 arr =" + Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++) {
            // digitOfElement对应放在哪个bucket中
            int digitOfElement = arr[i] / 10 % 10;
            // 放在第digitOfElement个bucket的第bucketElementCount[digitOfElement]下标位置
            bucket[digitOfElement][bucketElementCount[digitOfElement]] = arr[i];
            // 下次放在bucket的第bucketElementCount[digitOfElement]++下标位置
            bucketElementCount[digitOfElement]++;
        }

        arrIndex = 0;
        // 将桶中数据放回原数组
        for (int i = 0; i < bucketElementCount.length; i++) {
            // 对应桶中有数据
            if (bucketElementCount[i] != 0) {
                for (int j = 0; j < bucketElementCount[i]; j++) {
                    arr[arrIndex++] = bucket[i][j];
                }
            }
            // 将桶元素计数器清空
            bucketElementCount[i] = 0;
        }

        System.out.println("第2轮，对个位的排序处理 arr =" + Arrays.toString(arr));

        //for (int i = 0; i < bucket.length; i++) {
        //    for (int j = 0; j < bucket[0].length; j++) {
        //        System.out.printf("%d\t", bucket[i][j]);
        //    }
        //    System.out.println();
        //}

    }


}
