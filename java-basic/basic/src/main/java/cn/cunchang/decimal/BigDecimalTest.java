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
    public void testToString() {
        // 浮点数的打印
        System.out.println(new BigDecimal("10000000000").toString());
        // 普通的数字字符串
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
    public void testNewBigDecimal() {
        BigDecimal bigDecimal1 = new BigDecimal(1.23);
        String str1 = bigDecimal1.toString();
        System.out.println(str1);// 1.229999999999999982236431605997495353221893310546875
        BigDecimal bigDecimal2 = new BigDecimal("1.23");
        String str2 = bigDecimal2.toString();
        System.out.println(str2);// 1.23
    }

    @Test
    public void test保留小数() {
        BigDecimal bigDecimal = new BigDecimal("3.1415926572358452");

        BigDecimal bigDecimal1 = bigDecimal.setScale(4, RoundingMode.DOWN);
        // 保留四位，不会四舍五入
        System.out.println(bigDecimal1);// 3.1415

        BigDecimal bigDecimal2 = bigDecimal.setScale(4, RoundingMode.HALF_UP);
        // 保留四位，会四舍五入
        System.out.println(bigDecimal2);// 3.1416
    }
}
