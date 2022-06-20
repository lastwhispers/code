package cn.lastwhisper.atguigu.sort.heap;

import java.util.Arrays;

public class Heap {
    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 插入
     */
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
            swap(a, i, i / 2); // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
            i = i / 2;
        }
    }

    /**
     * 删除堆顶
     */
    public void removeMax() {
        if (count == 0) return; // 堆中没有数据
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

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
            if (i * 2 <= n && arr[i] < arr[i * 2]) {
                maxPos = i * 2;
            }
            // 待堆化元素左子树小于右子树，
            if (i * 2 + 1 <= n && arr[maxPos] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
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
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    // n 表示数据的个数，数组 a 中的数据从下标 1 到 n 的位置。
    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-99999, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(arr));
        Heap.sort(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}