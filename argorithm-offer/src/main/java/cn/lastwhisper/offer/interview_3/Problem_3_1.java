package cn.lastwhisper.offer.interview_3;

/**
 * 数组中重复的数字
 * 题目一：找出数组中重复的数字
 * @author lastwhisper
 */
public class Problem_3_1 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};
        duplicate(arr);
    }

    public static boolean duplicate(int[] arr) {
        // 检验数据
        if (arr == null || arr.length <= 0) {
            return false;
        }
        // 数字在0~n-1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return false;
            }
        }
        int temp, m;
        for (int i = 0; i < arr.length; i++) {
            // i== arr[i]时说明下标与对应数值相等
            while (arr[i] != i) {
                m = arr[i];
                //
                if (arr[i] == arr[m]) {
                    System.out.println(arr[i]);
                    return true;
                }
                temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            }
        }
        return false;
    }

    public static boolean duplicate1(int[] arr) {
        boolean flag = false;
        // 检验数据
        if (arr == null || arr.length <= 0) {
            return flag;
        }
        // 数字在0~n-1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return flag;
            }
        }
        int temp, m;
        for (int i = 0; i < arr.length; i++) {
            // i== arr[i]时说明下标与对应数值相等
            while (arr[i] != i) {
                m = arr[i];
                //
                if (arr[i] == arr[m]) {
                    flag = true;
                    System.out.println("重复数字："+arr[i]+" 重复下标："+i);
                    break;
                }
                temp = arr[i];
                arr[i] = arr[temp];
                arr[temp] = temp;
            }
        }
        return flag;
    }
}