package cn.lastwhisper.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author cunchang
 * @date 2022/4/29 12:24 AM
 */
public class UnSafeParkTest {

    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    // i == 5时，将当前线程挂起
                    unsafe.park(false, 0L);
                }
                System.out.println(Thread.currentThread().getName() + " printing i : " + i);
            }
        }, " Thread__Unsafe__1");

        t1.start();

        // 主线程休息三秒
        Thread.sleep(3000L);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " printing i : " + i);
            if (i == 9) {
                // 将线程 t1 唤醒
                unsafe.unpark(t1);
            }
        }

        System.in.read();
    }

}
