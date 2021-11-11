package cn.lastwhisper.jvm.tmp.heap.java1;

/** 测试：大对象直接进入老年代
 * -Xms60m -Xmx60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @author shkstart  shkstart@126.com
 * @create 2020  21:48
 */
public class YoungOldAreaTest {

    /**
     * young gen：20MB eden:16MB s0:2MB s1:2MB
     *   old gen：40MB
     *
     * Heap
     *  PSYoungGen      total 18432K, used 2997K [0x00000007bec00000, 0x00000007c0000000, 0x00000007c0000000)
     *   eden space 16384K, 18% used [0x00000007bec00000,0x00000007beeed420,0x00000007bfc00000)
     *   from space 2048K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007c0000000)
     *   to   space 2048K, 0% used [0x00000007bfc00000,0x00000007bfc00000,0x00000007bfe00000)
     *  ParOldGen       total 40960K, used 20480K [0x00000007bc400000, 0x00000007bec00000, 0x00000007bec00000)
     *   object space 40960K, 50% used [0x00000007bc400000,0x00000007bd800010,0x00000007bec00000)
     *  Metaspace       used 3215K, capacity 4496K, committed 4864K, reserved 1056768K
     *   class space    used 352K, capacity 388K, committed 512K, reserved 1048576K
     *
     * @param args
     */
    public static void main(String[] args) {
        // ParOldGen total 40960K, used 20480K
        byte[] buffer = new byte[1024 * 1024 * 20];//20m

    }
}
