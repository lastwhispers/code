package cn.lastwhisper.jvm.tmp.heap.java;

/**
 *
 *
 *
 * @author cunchang
 * @date 2021/9/20 4:58 下午
 */
public class HeapPartitionDemo {

    /**
     * JDK8 print gc detail
     * Heap
     *  PSYoungGen      total 2560K, used 1816K [0x00000007bfd00000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 2048K, 88% used [0x00000007bfd00000,0x00000007bfec60d8,0x00000007bff00000)
     *   from space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
     *   to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
     *  ParOldGen       total 7168K, used 0K [0x00000007bf600000, 0x00000007bfd00000, 0x00000007bfd00000)
     *   object space 7168K, 0% used [0x00000007bf600000,0x00000007bf600000,0x00000007bfd00000)
     *  Metaspace       used 3211K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
     */

    /**
     * 比较坑的地方就是要到它包名上一层目录，执行java命令运行
     * java -Xms10m -Xmx10m -XX:+PrintGCDetails  cn.lastwhisper.jvm.tmp.heap.java.HeapPartitionDemo
     *
     * JDK7 print gc detail
     *
     * Heap
     *  PSYoungGen      total 3072K, used 529K [0x00000007ffc80000, 0x0000000800000000, 0x0000000800000000)
     *   eden space 2560K, 20% used [0x00000007ffc80000,0x00000007ffd04420,0x00000007fff00000)
     *   from space 512K, 0% used [0x00000007fff80000,0x00000007fff80000,0x0000000800000000)
     *   to   space 512K, 0% used [0x00000007fff00000,0x00000007fff00000,0x00000007fff80000)
     *  ParOldGen       total 7168K, used 0K [0x00000007ff580000, 0x00000007ffc80000, 0x00000007ffc80000)
     *   object space 7168K, 0% used [0x00000007ff580000,0x00000007ff580000,0x00000007ffc80000)
     *  PSPermGen       total 21504K, used 2608K [0x00000007fa380000, 0x00000007fb880000, 0x00000007ff580000)
     *   object space 21504K, 12% used [0x00000007fa380000,0x00000007fa60c308,0x00000007fb880000)
     *
     */

    /**
     * -Xms10m -Xmx10m -XX:+PrintGCDetails
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("print gc detail");
    }


}
