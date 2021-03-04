package cn.lastwhisper.feature5.io.io.charstream;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class OutputStreamWriterDemo {

    /*
     * OutputStreamWriter(OutputStream out):根据默认编码把字节流的数据转换为字符流
     * OutputStreamWriter(OutputStream out,String charsetName):根据指定编码把字节流数据转换为字符流
     * 把字节流转换为字符流。
     * 字符流 = 字节流 +编码表。
     */
    @Test
    public void testWriteCoding() throws IOException {
        // 1、创建字符输出流
        // OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
        // "osw.txt")); // 默认GBK
        // OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
        // "osw.txt"), "GBK"); // 指定GBK
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(
                "osw.txt"), "UTF-8"); // 指定UTF-8
        // 2、写数据
        osw.write("中国");
        // 3、 释放资源
        osw.close();
    }

    /*
     * OutputStreamWriter的方法：
     * public void write(int c):写一个字符
     * public void write(char[] cbuf):写一个字符数组
     * public void write(char[] cbuf,int off,int len):写一个字符数组的一部分
     * public void write(String str):写一个字符串
     * public void write(String str,int off,int len):写一个字符串的一部分
     *
     * 面试题：close()和flush()的区别?
     * A:close()关闭流对象，但是先刷新一次缓冲区。关闭之后，流对象不可以继续再使用了。
     * B:flush()仅仅刷新缓冲区,刷新之后，流对象还可以继续使用。
     */
    @Test
    public void testWriteChar() throws IOException {
        // 1、创建字符输出流
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("osw2.txt"));

        // 2、写数据
        // public void write(int c):写一个字符
        // osw.write('a');
        // osw.write(97);
        // 为什么数据没有进去呢?
        // 原因是：字符 = 2字节
        // 文件中数据存储的基本单位是字节。
        // void flush()

        // public void write(char[] cbuf):写一个字符数组
        // char[] chs = {'a','b','c','d','e'};
        // osw.write(chs);

        // public void write(char[] cbuf,int off,int len):写一个字符数组的一部分
        // osw.write(chs,1,3);

        // public void write(String str):写一个字符串
        // osw.write("我爱林青霞");

        // public void write(String str,int off,int len):写一个字符串的一部分
        osw.write("我爱林青霞", 2, 3);

        // 刷新缓冲区
        osw.flush();
        // osw.write("我爱林青霞", 2, 3);

        // 3、释放资源
        osw.close();
        // java.io.IOException: Stream closed
        // osw.write("我爱林青霞", 2, 3);
    }

}