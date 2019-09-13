package cn.lastwhisper.jvm.memorystruct;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆溢出
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author lastwhisper
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
/**
 * java.lang.OutOfMemoryError: Java heap space
 * Dumping heap to java_pid16312.hprof ...
 * Heap dump file created [29573180 bytes in 0.111 secs]
 * Exception in Thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3720)
 * 	at java.base/java.util.Arrays.copyOf(Arrays.java:3689)
 * 	at java.base/java.util.ArrayList.grow(ArrayList.java:237)
 * 	at java.base/java.util.ArrayList.grow(ArrayList.java:242)
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:485)
 * 	at java.base/java.util.ArrayList.add(ArrayList.java:498)
 * 	at cn.lastwhisper.jvm.memorystruct.HeapOOM.main(HeapOOM.java:17)
 */