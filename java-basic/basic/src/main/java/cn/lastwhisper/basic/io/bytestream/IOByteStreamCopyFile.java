package cn.lastwhisper.basic.io.bytestream;

import org.junit.Test;

import java.io.*;

/**
 *
 * 字节流四种方式复制文件：
 * 基本字节流一次读写一个字节：	共耗时：117235毫秒
 * 基本字节流一次读写一个字节数组： 共耗时：156毫秒
 * 高效字节流一次读写一个字节： 共耗时：1141毫秒
 * 高效字节流一次读写一个字节数组： 共耗时：47毫秒
 *
 * @author lastwhisper
 * @date 2020/6/13
 */
public class IOByteStreamCopyFile {

    @Test
    public void testByteCopy() throws IOException {
        // 封装数据源
        InputStream fis = new FileInputStream("D:\\a.txt");
        // 封装目的地
        OutputStream fos = new FileOutputStream("D:\\b.txt");

        // 一个字节一个字节的复制
        int by;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }
        // 释放资源(先关谁都行)
        fos.close();
        fis.close();
    }

    @Test
    public void testByteBufferCopy() throws IOException {
        // 封装数据源
        InputStream fis = new FileInputStream("D:\\a.txt");
        // 封装目的地
        OutputStream fos = new FileOutputStream("D:\\b.txt");

        // 复制数据
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        // 释放资源(先关谁都行)
        fos.close();
        fis.close();
    }

}
