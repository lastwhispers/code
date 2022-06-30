package cn.cunchang;

import java.util.Random;

/**
 * @author cunchang
 * @date 2022/6/16 1:51 PM
 */
public class RandomTest {

    static int stock = 10000;

    public static void main(String[] args) {
        Random random = new Random();

        for (int i = 0; i < 1000000; i++) {
            int nextInt = random.nextInt(100);
            if (nextInt == 1) {
                if (!deduct()) {
                    break;
                }
                System.out.println("用户" + i + "抢到了");
            }
        }
    }

    public static boolean deduct() {
        if (stock < 0) {
            System.out.println("抢完了");
            return false;
        }
        stock--;
        return true;
    }

}
