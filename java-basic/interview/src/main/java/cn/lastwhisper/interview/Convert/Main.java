package cn.lastwhisper.interview.Convert;

/**
 * @author lastwhisper
 */
public class Main {
    private static String[] map = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E" };

    public static void main(String[] args) {
        int decimal = 235;
        int radix = 15;
        String ret = convert(decimal, radix);
        System.out.println(ret);
    }

    /**
     * @param decimal 表示待转换十进制数字
     * @param radix 表示需要转换成的进制数
     *     如decimal=235,radix=15.表示将十进制235转成15进制10A
     * @return 对应radix进制
     */
    public static String convert(int decimal, int radix) {
        if (radix > map.length) {
            throw new IllegalArgumentException("进制数不允许大于" + map.length);
        }
        if (decimal < radix) {
            return map[decimal];
        }
        StringBuilder sb = new StringBuilder();
        // 余数
        int remainder;
        // 被除数
        int divisor = decimal;
        while (divisor != 0 && divisor / radix >= 0) {
            remainder = divisor % radix; //取余
            divisor = divisor / radix; // 整除
            sb.append(map[remainder]);
        }
        return sb.reverse().toString();
    }
}
