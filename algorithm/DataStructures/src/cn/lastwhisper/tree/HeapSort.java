package cn.lastwhisper.tree;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author lastwhisper
 */
public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {4, 6, 8, 5, 9, 58, -10};
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println("数组：" + Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {

        //adjustHeap(arr, 1, arr.length);
        //System.out.println("第1次" + Arrays.toString(arr)); // 4,9,8,5,6

        //adjustHeap(arr, 0, arr.length);
        //System.out.println("第2次" + Arrays.toString(arr)); // 9,6,8,5,4
        int temp;
        // 1. 满二叉树从右向左、从下往上构建大顶堆；完全二叉树从左向右、从下往上构建大顶堆；
        // i--意味着，从下到上，每一次都是[i,arr.length]的数组（二叉树）进行重构大顶堆
        // [0,arr.length]重构了数组（二叉树）头部几个元素的大顶堆，同时也会重构下面的二叉树
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 2. 交换堆顶元素与末尾元素，调整堆结构
        for (int j = arr.length - 1; j >= 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组(二叉树), 调整成一个大顶堆

    /**
     * 功能： 将以i对应的非叶子结点的树调整成大顶堆
     * 举例  int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => {4, 9, 8, 5, 6} => {9, 6, 8, 5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param lenght 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int lenght) {
        // 缓存父结点
        int temp = arr[i];
        for (int k = 2 * i + 1; k < lenght; k = 2 * k + 1) {
            //  arr[k] < arr[k + 1]左结点小于右结点
            if (k + 1 < lenght && arr[k] < arr[k + 1]) {
                // k指向数值最大的结点
                k++;
            }
            if (arr[k] > temp) {//子节点大于父结点
                arr[i] = arr[k];//将子节点赋值到父结点
                i = k;//记录子结点的下标，为了最后 arr[i] = temp;
            } else {
                break;
            }

        }
        //arr[i]此时已经是子结点的位置了，前面父结点已经拿到了子结点的值了，这里要将子结点的值赋为父结点的值
        arr[i] = temp;
    }
}
