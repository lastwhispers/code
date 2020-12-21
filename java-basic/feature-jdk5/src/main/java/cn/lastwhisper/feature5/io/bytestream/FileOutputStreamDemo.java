package cn.lastwhisper.feature5.io.bytestream;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/13
 */
public class FileOutputStreamDemo {

    /*
     * IO流的分类：
     * 		流向：
     * 			输入流	读取数据
     * 			输出流 写出数据
     * 		数据类型：
     * 			字节流
     * 				字节输入流	读取数据	InputStream
     * 				字节输出流	写出数据	OutputStream
     * 			字符流
     * 				字符输入流	读取数据	Reader
     * 				字符输出流	写出数据	Writer
     *
     * 字节输出流操作步骤：
     * 		A:创建字节输出流对象
     * 		B:写数据
     * 		C:释放资源
     */

    @Test
    public void testWrite() throws IOException {
        // 1、创建字节输出流
        /*
         * 创建字节输出流对象了做了几件事情：
         * A:调用系统功能去创建文件
         * B:创建FileOutputStream对象
         * C:把FileOutputStream对象指向这个文件
         */
        File file = new File("fos.txt");
        OutputStream os = new FileOutputStream(file);
        //OutputStream os = new FileOutputStream("fos.txt");
        // 2、向字节输出流写入字节流
        os.write("hello java IO\n".getBytes());
        os.write("hello python IO".getBytes());
        // 3、释放资源
        /*
         * 为什么一定要close()呢?
         * A:让流对象变成垃圾，这样就可以被垃圾回收器回收了
         * B:通知系统去释放跟该文件相关的资源
         */
        os.close();
        //java.io.IOException: Stream Closed
        //os.write("java".getBytes());
    }

    @Test
    public void testWriteAppend() throws IOException {
         //1、创建字节输出流，追加写入数据
        OutputStream os = new FileOutputStream("fos.txt",true);
        // 2、向字节输出流写入字节流
        os.write(97);
        byte[] bytes={97,98,99,100,101};
        os.write(bytes);
        os.write(bytes,1,3);
        // 3、释放资源
        os.close();
    }

}
