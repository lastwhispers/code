package cn.lastwhisper.feature5.io.io.bytestream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/13
 */
public class BufferedOutputStreamDemo {

    /*
     * 通过定义数组的方式确实比以前一次读取一个字节的方式快很多，所以，看来有一个缓冲区还是非常好的。
     * 既然是这样的话，那么，java开始在设计的时候，它也考虑到了这个问题，就专门提供了带缓冲区的字节类。
     * 这种类被称为：缓冲区类(高效类)
     * 写数据：BufferedOutputStream
     * 读数据：BufferedInputStream
     *
     * 构造方法可以指定缓冲区的大小，但是我们一般用不上，因为默认缓冲区大小就足够了。
     *
     * 为什么不传递一个具体的文件或者文件路径，而是传递一个OutputStream对象呢?
     * 原因很简单，字节缓冲区流仅仅提供缓冲区，为高效而设计的。但是呢，真正的读写操作还得靠基本的流对象实现。
     */

    public static void main(String[] args) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream("bos.txt"));

        // 写数据
        bos.write("hello".getBytes());

        // 释放资源
        bos.close();
    }

}
