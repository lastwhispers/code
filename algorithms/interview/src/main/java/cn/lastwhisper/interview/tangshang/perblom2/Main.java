package cn.lastwhisper.interview.tangshang.perblom2;

import java.util.Scanner;

/**
 * @author lastwhisper
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int max = arr[0];
        int piovt = 0;
        while (piovt < n) {
            int sum = 0;
            for (int i = piovt; i < n; i++) {
                sum += arr[i];
                if (max < sum) {
                    max = sum;
                }
            }
            for (int i = 0; i < piovt; i++) {
                sum += arr[i];
                if (max < sum) {
                    max = sum;
                }
            }
            piovt++;
        }
        System.out.println(max);
    }
}
