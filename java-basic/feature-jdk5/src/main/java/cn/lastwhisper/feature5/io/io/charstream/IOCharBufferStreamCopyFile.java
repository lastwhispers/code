package cn.lastwhisper.feature5.io.io.charstream;

import org.junit.Test;

import java.io.*;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class IOCharBufferStreamCopyFile {

    @Test
    public void testCharBufferCopy() throws IOException {
        // 封装数据源
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));

        // 两种方式其中的一种一次读写一个字符数组
        char[] chars = new char[1024];
        int len ;
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
            bw.flush();
        }

        // 释放资源
        bw.close();
        br.close();
    }

    @Test
    public void testCharBufferCopy2() throws IOException {
        // 封装数据源
        BufferedReader br = new BufferedReader(new FileReader("a.txt"));
        // 封装目的地
        BufferedWriter bw = new BufferedWriter(new FileWriter("b.txt"));

        // 读写数据
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        // 释放资源
        bw.close();
        br.close();
    }


}
