package cn.lastwhisper.feature5.io.bytestream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author lastwhisper
 * @date 2020/6/13
 */
public class BufferedInputStreamDemo {

    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream("D:\\a.txt"));

        // 中文乱码
        //int by;
        //while ((by = bis.read()) != -1) {
        //System.out.print((char) by);
        //}

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            System.out.print(new String(buffer, 0, len));
        }
        // 释放资源
        bis.close();
    }

}
