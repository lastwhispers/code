package cn.lastwhisper.io.nio.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 单线程并发
 * 应用程序循环
 * @author lastwhisper
 */
public class MockNIOServer {
    static List<SocketChannel> list = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    try {
                        Thread.sleep(500);
                        System.out.println("no conn");
                        for (SocketChannel client : list) {
                            int length = client.read(byteBuffer);
                            if(length>0){
                                byteBuffer.flip();
                                // 处理业务
                                String content = new String(byteBuffer.array());
                                System.out.println(content);
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("conn-----------------------------");
                    socketChannel.configureBlocking(false);
                    list.add(socketChannel);
                    for (SocketChannel client : list) {
                        int length = client.read(byteBuffer);
                        if(length>0){
                            byteBuffer.flip();
                            // 处理业务
                            String content = new String(byteBuffer.array());
                            System.out.println(content);
                        }
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
