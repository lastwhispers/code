package cn.lastwhisper.offer.interview_3;

/**
 * 数组中重复的数字
 * 题目一：在n个数字0~n-1之间，找出数组中重复的数字
 * @author cn.lastwhisper
 */
public class Solution_3_1 {
    public static void main(String[] args) {
        //1.正常数据
        int[] arr1 = {2, 3, 1, 0, 2, 5, 3};
        //2.无重复数字
        int[] arr2 = {0, 1, 2, 5, 4, 3, 6};
        //3.长度为n数组包含0~n-1之外的数字
        //java.lang.ArrayIndexOutOfBoundsException: Index 100 out of bounds for length 7
        int[] arr3 = {0, 1, 2, 3, 4, 100, 100};
        //4.从非0开始的数组
        int[] arr4 = {2, 3, 1, 5, 2, 5, 3};
        duplicate(arr4);
    }

    public static boolean duplicate(int[] arr) {
        // 检验数据
        if (arr == null || arr.length <= 0) {
            System.out.println("数组为null！");
            return false;
        }
        for (int a : arr) {
            if (a < 1 || a > arr.length - 1) {
                System.out.println("数字大小超出范围！");
                return false;
            }
        }
        // 数字在0~n-1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return false;
            }
        }
        int temp;
        for (int i = 0; i < arr.length; i++) {
            // i== arr[i]时说明下标与对应数值相等
            while (arr[i] != i) {//while循环里面的代码是核心，找到arr[i]==arr[a[i]]时说明数值重复
                //
                if (arr[i] == arr[arr[i]]) {
                    System.out.println(arr[i]);
                    return true;
                } else {
                    temp = arr[i];
                    arr[i] = arr[temp];
                    arr[temp] = temp;
                }
            }
        }
        return false;
    }

}