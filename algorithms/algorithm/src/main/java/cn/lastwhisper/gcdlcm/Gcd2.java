package cn.lastwhisper.gcdlcm;

import org.junit.Assert;

/**
 * 相减法
 * @author lastwhisper
 * @date 2020/3/12
 */
class Gcd2 {

    /**
     * ⑵ 相减法
     * 有两整数a和b：
     *  ① 若a>b，则a=a-b
     *  ② 若a<b，则b=b-a
     *  ③ 若a=b，则a（或b）即为两数的最大公约数
     *  ④ 若a≠b，则再回去执行①
     * 例如求27和15的最大公约数过程为：
     *  27－15＝12( 15>12 )
     *  15－12＝3( 12>3 )
     *  12－3＝9( 9>3 )
     *  9－3＝6( 6>3 )
     *  6－3＝3( 3==3 )
     */
    public int gcd(int a, int b) {
        while (a != b) {
            if (a > b) a = a - b;
            else b = b - a;
        }
        return a;
    }

    public static void main(String[] args) {
        Gcd2 main = new Gcd2();
        Assert.assertEquals(3, main.gcd(27, 15));
    }
}
