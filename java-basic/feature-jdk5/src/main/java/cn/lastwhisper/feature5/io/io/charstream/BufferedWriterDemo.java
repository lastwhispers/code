package cn.lastwhisper.feature5.io.io.charstream;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class BufferedWriterDemo {

    /*
     * 字符流为了高效读写，也提供了对应的字符缓冲流。
     * BufferedWriter:字符缓冲输出流
     * BufferedReader:字符缓冲输入流
     *
     * BufferedWriter:字符缓冲输出流
     * 将文本写入字符输出流，缓冲各个字符，从而提供单个字符、数组和字符串的高效写入。
     * 可以指定缓冲区的大小，或者接受默认的大小。在大多数情况下，默认值就足够大了。
     */
    @Test
    public void testCharBufferWrite() throws IOException {
        // 1、创建字符缓冲输出流
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
        // new FileOutputStream("bw.txt")));
        BufferedWriter bw = new BufferedWriter(new FileWriter("bw.txt"));

        // 2、写入缓冲区
        bw.write("hello");
        bw.write("world");
        bw.write("java\n");
        bw.write("java 牛逼");
        bw.flush();

        // 3、关闭流
        bw.close();
    }

    @Test
    public void testCharBufferWrite2() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("bw2.txt"));
        for (int x = 0; x < 10; x++) {
            bw.write("hello" + x);
            // bw.write("\r\n");
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

}
