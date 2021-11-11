package cn.lastwhisper.jvm.tmp2.gclog;

import java.util.ArrayList;

/**
 * 测试生成详细的日志文件
 *
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
 *
 * @author shkstart
 * @create 14:27
 */
public class GCLogTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        for (int i = 0; i < 5000; i++) {
            byte[] arr = new byte[1024 * 50];//50KB
            list.add(arr);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}