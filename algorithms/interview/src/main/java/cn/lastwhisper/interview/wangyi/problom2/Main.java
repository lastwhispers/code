package cn.lastwhisper.interview.wangyi.problom2;

import java.util.Scanner;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            int count = 1;
            while (a+p < b) {
                int temp1 = a + p;
                int temp2 = a + p * q;
                if (temp1 > temp2) {
                    a = a + p;
                } else {
                    p = p * q;
                }
                count++;
            }
            System.out.println(count);
        }
    }

}
