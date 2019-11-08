package cn.lastwhisper.interview.tangshang.perblom3;

import java.util.Scanner;

/**
 * @author lastwhisper
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int x = in.nextInt();
        String xStr = String.valueOf(x);
        System.out.println(n - Integer.parseInt(xStr.substring(xStr.length() - 1)));
    }
}
