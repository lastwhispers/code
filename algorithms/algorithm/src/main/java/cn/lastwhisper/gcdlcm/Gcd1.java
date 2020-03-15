package cn.lastwhisper.gcdlcm;

import org.junit.Assert;

/**
 * 最小公倍数
 * @author lastwhisper
 * @date 2020/3/12
 */
class Gcd1 {

    /**
     * (1)辗转相除法
     * 有两整数a和b：
     *  ① a%b得余数c
     *  ② 若c=0，则b即为两数的最大公约数
     *  ③ 若c≠0，则a=b，b=c，再回去执行①
     *
     * 例如求27和15的最大公约数过程为：
     *  27÷15 余12
     *  15÷12 余3
     *  12÷3 余0 因此，3即为最大公约数
     */
    public int gcd(int a, int b) {
        int m=a,n=b;
        if (b > a) {
            int temp = a;a = b;b = temp;
        }

        int c;
        while (b != 0)  /* 余数不为0，继续相除，直到余数为0 */ {
            c = a % b;
            a = b;
            b = c;
        }
        return a;// m*n/a最小公倍数
    }

    public static void main(String[] args) {
        Gcd1 main = new Gcd1();
        Assert.assertEquals(3, main.gcd(27, 15));
    }
}
