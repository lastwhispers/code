package cn.lastwhisper.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author cunchang
 * @date 2022/4/29 12:24 AM
 */
public class UnSafeMemoryTest {
    /**
     * 堆外内存不受jvm内存模型掌控，在nio（netty，mina）中大量使用对外内存进行管道传输，copy等，
     * 使用它们的好处如下：
     *  对垃圾回收停顿的改善。由于堆外内存是直接受操作系统管理而不是JVM，所以当我们使用堆外内存时，即可保持较小的堆内内存规模。从而在GC时减少回收停顿对于应用的影响。
     *  提升程序I/O操作的性能。通常在I/O通信过程中，会存在堆内内存到堆外内存的数据拷贝操作，对于需要频繁进行内存间数据拷贝且生命周期较短的暂存数据，都建议存储到堆外内存。而在jdk中，堆外内存对应的类为DirectByteBuffer，它内部也是通过unsafe分配的内存:
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);

        // 分配 10M的堆外内存
        long _10M_Address = unsafe.allocateMemory(1 * 1024 * 1024 * 10);
        // 将10M内存的 前面1M内存值设置为10
        unsafe.setMemory(_10M_Address, 1 * 1024 * 1024 * 1, (byte) 10);
        // 获取第1M内存的值： 10
        System.out.println(unsafe.getByte(_10M_Address + 1000));
        // 获取第1M内存后的值： 0（没有设置）
        System.out.println(unsafe.getByte(_10M_Address + 1 * 1024 * 1024 * 5));
    }

}
