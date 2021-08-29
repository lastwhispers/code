package cn.cunchang.decimal;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author cunchang
 * @date 2021/3/11 4:00 下午
 */
public class BigDecimalTest {

    @Test
    public void testBigDecimal格式化输出() {
        // 浮点数的打印
        System.out.println(String.valueOf(new BigDecimal("1234567891231234.1234")));
        // 普通的数字字符串
        // 100.000
        System.out.println(new BigDecimal("100.000").toString());
        // 去除末尾多余的0
        System.out.println(new BigDecimal("100.000").stripTrailingZeros().toString());
        // 避免输出科学计数法
        System.out.println(new BigDecimal("100.000").stripTrailingZeros().toPlainString());
    }

    /**
     * 一定要用字符串形式的构造函数，否则会出现精度问题
     */
    @Test
    public void test构造BigDecimal的坑() {
        BigDecimal bigDecimal1 = new BigDecimal(1.23);
        String str1 = bigDecimal1.toString();
        System.out.println(str1);// 1.229999999999999982236431605997495353221893310546875
        BigDecimal bigDecimal2 = new BigDecimal("1.23");
        String str2 = bigDecimal2.toString();
        System.out.println(str2);// 1.23
    }

    /**
     *
     * BigDecimal.setScale()方法用于格式化小数点
     * setScale(1)表示保留一位小数，默认用四舍五入方式
     * setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
     * setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
     * setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
     * setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
     * setScaler(1,BigDecimal.ROUND_CEILING)接近正无穷大的舍入
     * setScaler(1,BigDecimal.ROUND_FLOOR)接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样
     * setScaler(1,BigDecimal.ROUND_HALF_EVEN)向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
     *
     */
    @Test
    public void test格式化小数点() {
        BigDecimal bigDecimal = new BigDecimal("3.1415926572358452");

        BigDecimal bigDecimal1 = bigDecimal.setScale(4, RoundingMode.DOWN);
        // 保留四位，不会四舍五入
        System.out.println(bigDecimal1);// 3.1415

        BigDecimal bigDecimal2 = bigDecimal.setScale(4, RoundingMode.HALF_UP);
        // 保留四位，会四舍五入
        System.out.println(bigDecimal2);// 3.1416
    }

    /**
     * 一定要使用compareTo
     */
    @Test
    public void test比较大小() {
        System.out.println(new BigDecimal("0").compareTo(new BigDecimal("0")) <= 0);
        System.out.println(new BigDecimal("1").compareTo(new BigDecimal("0")) <= 0);
    }


}
