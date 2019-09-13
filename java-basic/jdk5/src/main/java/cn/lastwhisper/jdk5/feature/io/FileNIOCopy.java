package cn.lastwhisper.jdk5.feature.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lastwhisper
 */
public class FileNIOCopy {
    public static void fastCopy(String src, String dist) throws IOException {
        /* 获得源文件的输入字节流 */
        FileInputStream fin = new FileInputStream(src);
        /* 获取输入字节流的文件通道 */
        FileChannel fcin = fin.getChannel();
        /* 获得目标文件的输入字节流 */
        FileInputStream fout = new FileInputStream(dist);
        /* 获取输出字节流的文件通道 */
        FileChannel fcout = fin.getChannel();

        /* 为缓冲区分配 1024 个字节 */
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            /* 从输入通道中读取数据到缓冲区中 */
            int read = fcin.read(byteBuffer);
            /* read() 返回 -1 表示 EOF */
            if (read == -1) {
                break;
            }
            /* 切换读写 */
            byteBuffer.flip();
            /* 把缓冲区的内容写入输出文件中 */
            fcout.write(byteBuffer);
            /* 清空缓冲区 */
            byteBuffer.clear();
        }

    }
}
