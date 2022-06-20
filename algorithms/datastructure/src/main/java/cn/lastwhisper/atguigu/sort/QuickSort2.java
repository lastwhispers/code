package cn.lastwhisper.atguigu.sort;

/**
 * @author lastwhisper
 */
public class QuickSort2 {
    public static void main(String[] args) {
//        int[] arr = {7, 3, 2, 8, 1, 9, 5, 4, 6};
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 0};//为什么left++和right--条件里面要加 left <= right 限定
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 10};
//        int arr[] = {7, 3, 2, 6, 8, 1, 9, 5, 4, 6, 10, 6, 6}; // 为什么不取等 arr[right] > pivot
//        int[] arr = {4, 6}; //小bug测试 // 为什么while (left <= right)里面要取等

//        int[] arr = {2, 10, 8, 22, 34, 5, 12, 28, 21, 11};
        int[] arr = {49, 38, 65, 97, 76, 13, 27};
//        int[] arr = {2, 10, 8, 1000};
        sort(arr, 0, arr.length - 1);
        print(arr);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    /**
     * @param arr 待分区数组
     * @param left 左边界
     * @param right 右边界
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int start = left, end = right - 1;
        //
        while (start <= end) {
            while (start <= end && arr[start] <= pivot) start++;
            while (start <= end && arr[end] > pivot) end--;
            if (start < end) swap(arr, start, end);
        }
        // 此时的start一定>=pivot(right)，所以需要交互
        swap(arr, start, right);
        return start;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr) {
        for (int value : arr) {
            System.out.print(value + "\t");
        }
    }
}
