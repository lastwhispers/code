package cn.lastwhisper.sort;

/**
 * @author lastwhisper
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6};
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 0};//为什么left++和right--条件里面要加 left <= right 限定
        //int arr[] = {7, 3, 2, 8, 1, 9, 5, 4, 6, 10};
        //int arr[] = {7, 3, 2, 6, 8, 1, 9, 5, 4, 6, 10, 6, 6}; // 为什么不取等 arr[right] > pivot
        //int arr[] = {4, 6}; //小bug测试 // 为什么while (left <= right)里面要取等
        sort(arr, 0, arr.length - 1);
        print(arr);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    // 分区
    private static int partition(int[] arr, int leftBound, int rightBound) {
        int left = leftBound;
        int pivot = arr[rightBound];
        int right = rightBound - 1;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left++;//left <= right是为了防止pivot最大，找不到比它大的数，导致越界
            while (left <= right && arr[right] > pivot) right--;
            System.out.println("left:" + left + "\tright:" + right);
            if (left < right) swap(arr, left, right);
        }
        swap(arr, left, rightBound);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }
}
