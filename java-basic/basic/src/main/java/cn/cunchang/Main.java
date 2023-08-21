package cn.cunchang;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kaisui
 * @description
 * @date 2022/12/26
 */
public class Main {
    static Map<Integer, Integer> map = new HashMap<>();

    /**
     * 判断数组中的数是否可以组成另一个数
     * a*n+b*m=c
     */
    public static void main(String[] args) {
        // a*n+b*m=c
        //1、n=1、m=1 || n=0||m==0
        Integer[] arr = {50, 10};
        int c = 60;
        //2、n>1，m=1 || n=1，m>1
//        Integer[] arr = {50, 10};
//        int c = 110;
        //
        //3、n>1，m>1
//        Integer[] arr = {50, 90};
//        Integer[] arr = {50, 80};
//        int c = 260;
        boolean xxxx = xxxx(arr, c);
        if (xxxx) {
            System.out.println("找到了：" + JSON.toJSONString(map));
        } else {
            System.out.println("没找到");
        }
    }

    private static boolean xxxx(Integer[] arr, int c) {
        if (c == 0) {
            return true;
        }
        if (c < 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            c = c - arr[i];
            if (xxxx(arr, c)) {
                return true;
            }
            // 回溯
            c = c + arr[i];
            map.put(arr[i], map.get(arr[i]) - 1);
        }
        return false;
    }
}
