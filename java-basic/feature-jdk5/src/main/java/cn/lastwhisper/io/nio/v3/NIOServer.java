package cn.lastwhisper.io.nio.v3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 操作系统循环
 * @author lastwhisper
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 1、创建选择器
        Selector selector = Selector.open();
        // 2、将通道注册到选择器上
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 设置非阻塞
        ssChannel.configureBlocking(false);
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 3、监听事件
        ServerSocket serverSocket = ssChannel.socket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 8080));

        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            // 5、事件循环
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                // 4、获取到达的事件
                if (key.isAcceptable()) {
                    ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel socketChannel = ssChannel1.accept();
                    socketChannel.configureBlocking(false);
                    // 这个新连接主要用于从客户端读取数据
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(sChannel));
                    sChannel.close();
                }
                keyIterator.remove();
            }

        }

    }

    private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder data = new StringBuilder();
        while (true) {
            buffer.clear();
            int n = sChannel.read(buffer);
            if (n == -1) {
                break;
            }
            buffer.flip();
            int limit = buffer.limit();
            char[] dst = new char[limit];
            for (int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }
        return data.toString();
    }
}
