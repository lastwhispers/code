package cn.lastwhisper.interview.other;

import java.util.Scanner;

/**
 *给出一个长度为n的数组a，你需要在这个数组中找到一个长度至少为m的区间，
 * 使得这个区间内的数字的和尽可能小。
 * @author lastwhisper
 */
public class Main {

    public int sum(int[] numbers, int m) {
        if (numbers.length < m)
            throw new IllegalArgumentException("Illegal argument numbers");
        int l = 0, r = numbers.length - 1, x = m;
        int min = 0;
        for (int i = l; i <= r; i++) {
            min += numbers[i];
        }
        for (int i = 0; i <= (r - m); i++) {
            int temp = 0;
            for (int j = i; j < x; j++) {
                temp += numbers[j];
            }
            if (temp < min) {
                min = temp;
            }
            x++;
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 一个while就是一个测试用例
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[] nums = new int[m];
            for (int i = 0; i < m; i++) {
                nums[i] = scanner.nextInt();
            }
            //int[] nums = {-2, 1, -1, -1, -1};
            //int n = 3;
            System.out.println(new Main().sum(nums, n));
        }

    }

}
