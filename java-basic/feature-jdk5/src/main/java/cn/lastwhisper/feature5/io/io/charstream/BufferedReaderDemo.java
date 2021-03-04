package cn.lastwhisper.feature5.io.io.charstream;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/14
 */
public class BufferedReaderDemo {

    @Test
    public void testCharRead() throws IOException {
        // 1、创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader("bw.txt"));

        // 2、读输入流
        // 方式1
        // int ch = 0;
        // while ((ch = br.read()) != -1) {
        // System.out.print((char) ch);
        // }

        // 方式2
        char[] chars = new char[1024];
        int len;
        while ((len = br.read(chars)) != -1) {
            System.out.print(new String(chars, 0, len));
        }

        // 3、释放资源
        br.close();
    }


    @Test
    public void testCharRead2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bw2.txt"));

        // 最终版代码
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        //释放资源
        br.close();
    }

}
