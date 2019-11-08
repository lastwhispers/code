package cn.lastwhisper.interview.tangshang.perblom3;

import java.util.Scanner;

/**
 * @author lastwhisper
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Long n = in.nextLong();
        Long x = in.nextLong();
        for (Long i = n - x >= 10 ? n - x : 10; i < n; i++) {
            String ret = convert(n, i);
            if (Long.parseLong(ret) <= x) {
                System.out.println(i); //进制
                break;
            }
        }

    }


    public static String convert(Long decimal, Long radix) {
        StringBuilder sb = new StringBuilder();
        // 余数
        Long remainder = -1l;
        // 被除数
        Long divisor = decimal;
        while (divisor != 0 && divisor / radix >= 0) {
            remainder = divisor % radix; //取余
            divisor = divisor / radix; // 整除
            sb.append(remainder);
        }
        return sb.reverse().toString();
    }
}
