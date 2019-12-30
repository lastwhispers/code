package book.chapter08.$8_5_2;

/**
 * 三机房部署方案
 */
public class HostAssignment_3 {

    static int n = 9;

    public static void main(String[] args) {
        int n1, n2, n3;
        n1 = (n - 1) / 2;
        int n2_max = (n - n1) / 2;
        for (int i = 1; i <= n2_max; i++) {
            n2 = i;
            n3 = n - n1 - n2;
            if (n3 >= (n1 + n2)) {
                continue;
            }
            System.out.println("( " + n1 + ", " + n2 + ", " + n3 + " )");
        }
    }
}
