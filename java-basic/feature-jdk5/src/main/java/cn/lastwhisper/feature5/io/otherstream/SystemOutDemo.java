package cn.lastwhisper.feature5.io.otherstream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/14
 */
public class SystemOutDemo {


    /**
     * 如何将数据输出到控制台？
     * System.out是字节输出流，需要使用字符输出流包装
     * 再使用 BufferedWriter 打印一行数据
     */
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("hello");
        bw.newLine();
        // bw.flush();
        bw.write("world");
        bw.newLine();
        // bw.flush();
        bw.write("java");
        bw.newLine();
        bw.flush();//打断点，只有调用 flush 方法才会输出到控制台

        bw.close();
    }

}
