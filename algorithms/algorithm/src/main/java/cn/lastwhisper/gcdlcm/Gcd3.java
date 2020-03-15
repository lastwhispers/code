package cn.lastwhisper.gcdlcm;

import org.junit.Assert;

/**
 * 穷举法
 * @author lastwhisper
 * @date 2020/3/12
 */
class Gcd3 {

    // https://www.cnblogs.com/ECJTUACM-873284962/p/6679839.html
    public int gcd(int a, int b) {
        int t = 0;
        for (int i = 1; i <= a; i++)
            if (a % i == 0 && b % i == 0) t = i;
        return t;
    }

    public static void main(String[] args) {
        Gcd3 main = new Gcd3();
        Assert.assertEquals(3, main.gcd(27, 15));
    }
}
