package cn.lastwhisper.feature5.io.otherstream;

import org.junit.Test;

import java.io.*;

/**
 *
 * @author lastwhisper
 * @date 2020/6/14
 */
public class PrintWriterDemo {

    /*
     * 打印流
     * 字节流打印流	PrintStream
     * 字符打印流	PrintWriter
     *
     * 打印流的特点：
     * 		A:只有写数据的，没有读取数据。只能操作目的地，不能操作数据源。
     * 		B:可以操作任意类型的数据。
     * 		C:如果启动了自动刷新，能够自动刷新。
     * 		D:该流是可以直接操作文本文件的。
     * 			哪些流对象是可以直接操作文本文件的呢?
     * 			FileInputStream
     * 			FileOutputStream
     * 			FileReader
     * 			FileWriter
     * 			PrintStream
     * 			PrintWriter
     * 			看API,查流对象的构造方法，如果同时有File类型和String类型的参数，一般来说就是可以直接操作文件的。
     *
     * 			流：
     * 				基本流：就是能够直接读写文件的
     * 				高级流：在基本流基础上提供了一些其他的功能
     */
    @Test
    public void testPrintWriter() throws FileNotFoundException {
        // 作为Writer的子类使用
        PrintWriter pw = new PrintWriter("pw.txt");

        pw.write("hello");
        pw.write("world");
        pw.write("java");
        pw.close();
    }

    /*
     * 1:可以操作任意类型的数据。
     * 		print()
     * 		println()
     * 2:启动自动刷新
     * 		PrintWriter pw = new PrintWriter(new FileWriter("pw2.txt"), true);
     * 		还是应该调用println()的方法才可以
     * 		这个时候不仅仅自动刷新了，还实现了数据的换行。
     *
     * 		println()
     *		其实等价于于：
     *		bw.write();
     *		bw.newLine();
     *		bw.flush();
     */
    @Test
    public void testPrintWriter2() throws IOException {
        // 创建打印流对象
        // PrintWriter pw = new PrintWriter("pw2.txt");
        PrintWriter pw = new PrintWriter(new FileWriter("pw2.txt"), true);

        // write()只能写字节和字符
        // println()可以写任意类型数据
        pw.println("hello");
        pw.println(true);
        pw.println(100);
        pw.close();
    }


    @Test
    public void testCopy() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(
                "DataStreamDemo.java"));
        // 封装目的地
        PrintWriter pw = new PrintWriter(new FileWriter("Copy.java"), true);

        String line;
        while ((line = br.readLine()) != null) {
            pw.println(line);
        }

        pw.close();
        br.close();
    }
}
