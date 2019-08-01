package cn.lastwhisper.search;

import cn.lastwhisper.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lastwhisper
 */
public class BinarySearch {
    private static int count = 0;

    public static void main(String[] args) {
        // 无重复值
        //int[] arr = {1, 8, 10, 89, 1000, 1234};

        int[] arr = ArrayUtil.generateArrByOrder(100);
        int i = binarySearch1(arr, 35);
        System.out.println("查找次数："+count);
        //int i = binarySearch2(arr, 0, arr.length - 1, 11);
        if (i == -1) {
            System.out.println("没有找到");
        } else {
            System.out.printf("找到了！下标为：%d，数值为：%d", i, arr[i]);
        }
        // 有重复值
        //int[] arr = {1, 1, 1, 1, 10, 89, 1000, 1234};
        //int[] arr = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
        //List<Integer> indexs = binarySearch3(arr, 1);
        //List<Integer> indexs = binarySearch4(arr, 0, arr.length - 1, 1);
        //for (Integer integer : indexs) {
        //    System.out.println(integer);
        //}
    }

    /**
     * 二分搜索（非递归）
     * Xae=ro/iA1li
     * @param arr
     * @param target
     * @return int
     */
    public static int binarySearch1(int[] arr, int target) {
        if (target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        int left = 0; //[0..mid] [mid+1...arr.length-1]
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        while (left < right) {
            if (target == arr[mid]) {
                return mid;
            } else if (target > arr[mid]) {
                // 在mid的右边找
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
            count++;
        }
        return -1;
    }

    /**
     * 二分搜索（递归）
     *
     * @param arr
     * @param left
     * @param right
     * @param target
     * @return int
     */
    public static int binarySearch2(int[] arr, int left, int right, int target) {
        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (target == arr[mid]) {
            return mid;
        } else if (target > arr[mid]) {
            // 在mid的右边找
            return binarySearch2(arr, mid + 1, right, target);
        } else {
            // 在mid的左边找
            return binarySearch2(arr, left, mid - 1, target);
        }
    }

    /**
     * 二分搜索（非递归，可重复值）
     *
     * @param arr
     * @param target
     * @return int
     */
    public static List<Integer> binarySearch3(int[] arr, int target) {
        int left = 0; //[0..mid] [mid+1...arr.length-1]
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        List<Integer> indexs = new ArrayList<>();
        while (left < right) {
            if (target == arr[mid]) {
                int midTemp = mid;

                while (midTemp >= left && arr[midTemp] == target) {
                    indexs.add(midTemp);
                    midTemp--;
                }
                // 因为[left...mid]已经被扫描过了，所以mid先加，接着扫描[mid+1...right]
                mid++;
                while (mid <= right && arr[mid] == target) {
                    indexs.add(mid);
                    mid++;
                }
                break;
            } else if (target > arr[mid]) {
                // 在mid的右边找
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }
        return indexs;
    }

    /**
     * 二分搜索（递归，可重复值）
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return int
     */
    public static List<Integer> binarySearch4(int[] arr, int left, int right, int findVal) {

        System.out.println("hello~");
        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) { // 向 右递归
            return binarySearch4(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 向左递归
            return binarySearch4(arr, left, mid - 1, findVal);
        } else {
//			 * 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp -= 1; //temp左移
            }
            resIndexlist.add(mid);  //

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {//退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                temp += 1; //temp右移
            }

            return resIndexlist;
        }

    }

}
