package cn.lastwhisper.feature5.io.nio;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/*
 * 一、通道(Channel):用于源节点与目标节点的连接。在java NIO中负责缓冲区中数据的传输。Channel本身不存储数据，需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 *    java.nio.channels.Channel 接口：
 *        |--FileChannel：用于读取、写入、映射和操作文件的通道。
 *        |--SocketChannel：通过 TCP 读写网络中的数据。
 *        |--ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。
 *        |--DatagramChannel：通过 UDP 读写网络中的数据通道。
 *
 * 三、获取通道
 * 1.java针对支持通道的类提供了getChannel()方法
 *      本地IO：
 *      FileInputStream/FileOutputStream
 *      RandomAccessFile
 *
 *      网络IO：
 *      Socket
 *      ServerSocket
 *      DatagramSocket
 *
 * 2.在JDK 1.7 中的NIO.2 针对各个通道提供了静态方法 open()
 * 3.在JDK 1.7 中的NIO.2 的Files工具类的newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串-》字符数组
 * 解码：字符数组-》字符串
 */
public class TestChannel {

    //利用通道完成文件的复制(非直接缓冲区)
    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        try (
                FileInputStream fis = new FileInputStream("d:/59035.zip");
                FileOutputStream fos = new FileOutputStream("d:/59036.zip");
                //1.获取通道
                FileChannel inChannel = fis.getChannel();
                FileChannel outChannel = fos.getChannel();) {

            //2.分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //3.将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                //切换读取数据的模式
                buf.flip();
                //4.将缓冲区中的数据写入通道中
                outChannel.write(buf);
                buf.clear();//清空缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start));//耗费时间：1094
    }

    //使用直接缓冲区完成文件的复制(内存映射文件)
    @Test
    public void test2() {
        long start = System.currentTimeMillis();

        try (
                //1.获取通道
                FileChannel inChannel = FileChannel.open(Paths.get("d:/59035.zip"), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get("d:/59036.zip"),
                        StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);) {

            //2.内存映射文件
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //3.直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start));//耗费的时间为：200
    }

    //通道之间的数据传输(直接缓冲区)
    @Test
    public void test3() {
        long start = System.currentTimeMillis();

        try (
                FileChannel inChannel = FileChannel.open(Paths.get("d:/59035.zip"), StandardOpenOption.READ);
                FileChannel outChannel = FileChannel.open(Paths.get("d:/59036.zip"),
                        StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);) {

            inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start));//耗费的时间为：147
    }

    // 分散和聚合
    @Test
    public void test4() {
        try (
                RandomAccessFile raf1 = new RandomAccessFile("d:/1.txt", "rw");
                RandomAccessFile raf2 = new RandomAccessFile("d:/2.txt", "rw");
                //1.获取通道
                FileChannel channel1 = raf1.getChannel();
                FileChannel channel2 = raf2.getChannel();) {
            //2.分配指定大小的缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(512);
            ByteBuffer buf2 = ByteBuffer.allocate(1024);
            //3.分散读取
            ByteBuffer[] bufs = {buf1, buf2};
            channel1.read(bufs);
            for (ByteBuffer buf : bufs) {
                buf.flip();
            }
            System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
            System.out.println("--------------------");
            System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

            //4.聚集写入
            channel2.write(bufs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输出支持的字符集
    @Test
    public void test5() {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    //字符集
    @Test
    public void test6() {
        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();

        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("啦啦哈哈吧吧");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = null;
        try {
            bBuf = ce.encode(cBuf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < bBuf.limit(); i++) {
            System.out.println(bBuf.get());//-64-78-64-78-71-2-7-2-80-55-80-55
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = null;
        try {
            cBuf2 = cd.decode(bBuf);
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        System.out.println(cBuf2.toString());//啦啦哈哈吧吧
    }

}

