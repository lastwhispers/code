package cn.lastwhisper.io.io.charstream;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class InputStreamReaderDemo {


    /*
     * InputStreamReader的方法：
     * int read():一次读取一个字符
     * int read(char[] chs):一次读取一个字符数组
     */
    @Test
    public void testCharRead() throws IOException {
        // 1、创建字符输入流
        // InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"));

        // InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"), "GBK");
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("osw.txt"), "UTF-8");

        // 2、写入数据
        // 一次读取一个字符
        int ch;
        while ((ch = isr.read()) != -1) {
            System.out.print((char) ch);
        }

        // 3、释放资源
        isr.close();
    }

    @Test
    public void testCharBufferRead() throws IOException {
        // 1、创建字符输入流
        // InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"));

        // InputStreamReader isr = new InputStreamReader(new FileInputStream("osw.txt"), "GBK");
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream("osw.txt"), "UTF-8");

        // 2、写入数据
        // 一次读取一个字符数组
        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            System.out.print(new String(chars, 0, len));
        }

        // 3、释放资源
        isr.close();
    }

}
