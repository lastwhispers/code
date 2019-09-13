package cn.lastwhisper.jvm.memorystruct;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.jdk6环境下，String.intern()，运行时常量池导致的内存溢出异常
 * Exception in Thread "main" java.lang.OutOfMemoryError: PermGen space
 * 	at java.lang.String.intern(Native Method)
 * 	at cn.lastwhisper.jvm.memorystruct.PermGenOOM.main(PermGenOOM.java from InputFileObject:16)
 * intern()将堆中String对象，复制到常量池
 * 2.jdk7环境下，不会内存溢出
 * intern()常量池引用堆中String对象
 * 3.jdk8环境下，不会内存溢出，并提示
 *  Java HotSpot(TM) 64-Bit Server VM warning: Ignoring option PermSize; support was removed in 8.0
 *  Java HotSpot(TM) 64-Bit Server VM warning: Ignoring option MaxPermSize; support was removed in 8.0
 *
 *  VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 * @author lastwhisper
 */
public class PermGenOOM {
    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        for (int i = 0; ; i++) {
            list.add(String.valueOf(i).intern());
        }
    }
}