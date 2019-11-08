package cn.lastwhisper.leetcode.hashtable.快乐数_202_Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lastwhisper
 */
public class Test {

    public boolean isHappy(int n) throws InterruptedException {
        System.out.println(n);
        while (true) {
            String[] arr = split(n);
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += Integer.parseInt(arr[i]) * Integer.parseInt(arr[i]);
                if (i == arr.length - 1) {
                    System.out.printf("%s^2 = ", arr[i]);
                } else {
                    System.out.printf("%s^2 + ", arr[i]);
                }
            }
            System.out.println(sum);
            if (sum == 1) {
                return true;
            }
            n = sum;
            Thread.sleep(100);
        }
    }

    public String[] split(int num) {
        String numStr = String.valueOf(num);
        return numStr.split("");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Test().isHappy(18));
    }
}

