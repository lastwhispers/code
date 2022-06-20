package cn.lastwhisper.atguigu.sort.heap;

import java.util.Arrays;

public class Heap2 {


    /**
     * 堆化大顶堆
     * 自上往下堆化
     *
     * @param arr 数组
     * @param n   堆中元素个数
     * @param i   待堆化的元素
     */
    private static void heapify(int[] arr, int n, int i) {
        while (true) {
            // 待堆化元素、左、右子树最大值的下标
            int maxPos = i;
            // 待堆化元素小于左子树
            if (i * 2 + 1 <= n && arr[i] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 待堆化元素左子树小于右子树，
            if (i * 2 + 2 <= n && arr[maxPos] < arr[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            // 最大元素是自己无需向下堆化
            if (maxPos == i) break;
            // 最大元素不是自己，交互值
            swap(arr, i, maxPos);
            // 将被交互的元素继续向下堆化
            i = maxPos;
        }
    }

    private static void buildHeap(int[] a, int n) {
        // 对非叶子节点进行，自上往下堆化
        for (int i = n / 2 - 1; i >= 0; --i) {
            heapify(a, n, i);
        }
    }

    public static void sort(int[] arr, int n) {
        // 1. 构建大顶堆
        buildHeap(arr, n);
        int disorder = n;
        // 2. 交换堆顶元素与末尾元素，调整堆结构
        while (disorder > 0) {
            swap(arr, 0, disorder);
            disorder--;
            heapify(arr, disorder, 0);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 10, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Heap2.sort(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}