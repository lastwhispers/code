package cn.lastwhisper.feature5.io.io.charstream;

import org.junit.Test;

import java.io.*;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class IOCharStreamCopyFile {

    @Test
    public void testCharCopy() throws IOException {
        // 1、创建字符输入输出流
        InputStreamReader isr = new InputStreamReader(new FileInputStream("d:\\a.txt"));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\b.txt"));

        // 2、将字符输入流的数据读出来，写到字符输出流中
        // 一个字符一个字符的读写
        //int ch;
        //while ((ch = isr.read()) != -1) {
        //    osw.write(ch);
        //}

        int len;
        char[] cbuf = new char[1024];
        while ((len = isr.read(cbuf)) != -1) {
            osw.write(cbuf, 0, len);
        }

        // 3、关闭流
        osw.close();
        isr.close();
    }

    /*
     * 由于我们常见的操作都是使用本地默认编码，所以，不用指定编码。
     * 而转换流的名称有点长，所以，Java就提供了其子类供我们使用。
     * OutputStreamWriter = FileOutputStream + 编码表(GBK)
     * FileWriter = FileOutputStream + 编码表(GBK)
     *
     * InputStreamReader = FileInputStream + 编码表(GBK)
     * FileReader = FileInputStream + 编码表(GBK)
     *
     */
    @Test
    public void testFileRWCopy() throws IOException {
        // 1、创建字符输入输出流
        FileReader fr = new FileReader("d:\\a.txt");
        FileWriter fw = new FileWriter("d:\\b.txt");

        // 一次一个字符
        // int ch = 0;
        // while ((ch = fr.read()) != -1) {
        // fw.write(ch);
        // }

        // 一次一个字符数组
        char[] chs = new char[1024];
        int len = 0;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
            fw.flush();
        }

        // 释放资源
        fw.close();
        fr.close();
    }


}
