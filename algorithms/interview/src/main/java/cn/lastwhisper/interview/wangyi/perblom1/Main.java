package cn.lastwhisper.interview.wangyi.perblom1;

import java.util.Scanner;

/**
 *
 * @author lastwhisper
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            System.out.println(min(arr[i]));
        }
    }

    public static String min(int x) {
        int n9 = x / 9; // 5
        int a = x % 9; // 余数
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n9; i++) {
            sb.append("9");
        }
        if (a != 0) {
            sb.append(a);
        }
        return sb.reverse().toString();
    }

}
