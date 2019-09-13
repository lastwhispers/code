package cn.lastwhisper.interview.Exception;

/**
 * finally中return
 * @author lastwhisper
 */
public class FinallyReturnTest {
    public static int finallyreturn(int n) {
        try {
            int r = n * n;
            return r;
        } finally {
            if (n == 2) return 0;
        }
    }

    public static void main(String[] args) {
        int f = finallyreturn(2);
        System.out.println(f);
    }
}
