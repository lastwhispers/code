package cn.lastwhisper.interview.tangshang.perblom1;

import java.util.*;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        System.out.println(move(n, m));
    }

    public static int move(int n, int m) {

        int count = 0;
        if (n == 1 || m == 1) {
            return 1;
        }
        if (n > 1) {
            count += move(n - 1, m);
        }
        if (m > 1) {
            count += move(n, m - 1);
        }
        return count;
    }
}
