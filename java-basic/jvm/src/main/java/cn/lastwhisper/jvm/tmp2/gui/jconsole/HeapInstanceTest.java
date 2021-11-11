package cn.lastwhisper.jvm.tmp2.gui.jconsole;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600m -Xmx600m -XX:SurvivorRatio=8
 * @author shkstart  shkstart@126.com
 * @create 2020  17:51
 */
public class HeapInstanceTest {
    byte[] buffer = new byte[new Random().nextInt(1024 * 100)];

    public static void main(String[] args) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<HeapInstanceTest> list = new ArrayList<HeapInstanceTest>();
        while (true) {
            list.add(new HeapInstanceTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
