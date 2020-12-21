package cn.lastwhisper.feature5.io.bytestream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author lastwhisper
 * @date 2020/6/13
 */
public class FileInputStreamDemo {

    /*
     * 字节输入流操作步骤：
     * A:创建字节输入流对象
     * B:调用read()方法读取数据，并把数据显示在控制台
     * C:释放资源
     *
     * 读取数据的方式：
     * A:int read():一次读取一个字节
     * B:int read(byte[] b):一次读取一个字节数组
     */
    @Test
    public void testByteRead() throws IOException {
        // 1、创建字节输入流对象
        InputStream is = new FileInputStream("fos.txt");
        // 2、调用read()方法读取数据
        // 一个字节一个字节的读，中文表示需要两个字节，会乱码
        int by;
        while ((by = is.read()) != -1) {
            System.out.print((char)by);
        }
        // 3、释放资源
        is.close();
    }

    @Test
    public void testBufferRead() throws IOException {
        // 1、创建字节输入流对象
        InputStream is = new FileInputStream("fos.txt");
        // 2、调用read()方法读取数据
        // 通过字节数组缓冲区，优化性能
        int len;
        byte[] buffer = new byte[1024];
        while ((len = is.read(buffer)) != -1) {
            System.out.println(new String(buffer, 0, len));
        }
        // 3、释放资源
        is.close();
    }


}
