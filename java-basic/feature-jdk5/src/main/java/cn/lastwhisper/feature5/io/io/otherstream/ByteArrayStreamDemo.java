package cn.lastwhisper.feature5.io.io.otherstream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author lastwhisper
 * @date 2020/6/14
 */
public class ByteArrayStreamDemo {

    /*
     * 内存操作流：用于处理临时存储信息的，程序结束，数据就从内存中消失。
     * 字节数组：
     * 		ByteArrayInputStream
     * 		ByteArrayOutputStream
     * 字符数组：
     * 		CharArrayReader
     * 		CharArrayWriter
     * 字符串：
     * 		StringReader
     * 		StringWriter
     */
    public static void main(String[] args) throws IOException {
        // 写数据
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 写数据
        for (int x = 0; x < 10; x++) {
            baos.write(("hello" + x).getBytes());
        }

        // 释放资源，内存操作流不需要释放资源
         baos.close();

        byte[] bys = baos.toByteArray();

        // 读数据
        ByteArrayInputStream bais = new ByteArrayInputStream(bys);

        int by;
        while ((by = bais.read()) != -1) {
            System.out.print((char) by);
        }

         bais.close();

    }

}
