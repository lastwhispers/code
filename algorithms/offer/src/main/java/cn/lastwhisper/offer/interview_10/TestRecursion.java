package cn.lastwhisper.offer.interview_10;

/**
 * 递归与循环测试
 * @author cn.lastwhisper
 */
public class TestRecursion {
    public static void main(String[] args) {
        TestRecursion recursion = new TestRecursion();
        //int sum = recursion.addFrom1ToN_Recursion(100000);
        int sum = recursion.addFrom1ToN_Iterative(100000);
        System.out.println(sum);

    }


    private int addFrom1ToN_Recursion(int n) {
        return n < 0 ? 0 : n + addFrom1ToN_Recursion(n - 1);
    }

    private int addFrom1ToN_Iterative(int n) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }
}
