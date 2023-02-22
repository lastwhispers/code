package cn.lastwhisper.io.nio.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 单线程并发
 * 应用程序循环
 *
 * @author lastwhisper
 */
public class MockNIOServer {
    static List<SocketChannel> list = new ArrayList<>();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

    public static void main(String[] args) throws InterruptedException {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            // 设置非阻塞
            serverSocketChannel.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel == null) {
                    Thread.sleep(500);
                    boolean empty = handleChannelList();
                    System.out.printf("date:%s,没有新的连接，处理已有通道集合 empty:%s\n", new Date(),empty);
                } else {
                    System.out.printf("date:%s,有新的连接，加入通道集合，处理通道集合\n", new Date());
                    // 设置客户端和服务端之间的通道非阻塞，不设置的话socketChannel#read无数据就会阻塞
                    socketChannel.configureBlocking(false);
                    list.add(socketChannel);
                    handleChannelList();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean handleChannelList() throws IOException, InterruptedException {
        if(list.isEmpty()){
            return true;
        }
        Iterator<SocketChannel> iterator = list.iterator();
        while (iterator.hasNext()){
            SocketChannel socketChannel = iterator.next();
            Thread.sleep(500);// 减少日志打印
            Date current = new Date();
            // 如果前面没有设置socketChannel.configureBlocking(false)，这里read不到数据就会阻塞
//            System.out.printf("date:%s,通道id:%s,尝试读数据\n", current, socketChannel.hashCode());
            int length = socketChannel.read(byteBuffer);// length=2('1'、'\n')
            if (length > 0) {
                byteBuffer.flip();// 切换到读模式
                Thread.sleep(500);// 模拟业务处理耗时
                byte[] dst = new byte[byteBuffer.limit()];
                byteBuffer.get(dst);//读取数据到dst数组
                String content = new String(dst, 0, dst.length);
                byteBuffer.clear();// 清空Buffer
                System.out.printf("date:%s,通道id:%s,读到了数据:%s\n", current, socketChannel.hashCode(), content);
                if (content.equals("exit") || content.equals("exit\n")) {
                    socketChannel.close();
                    iterator.remove();
                    System.out.printf("date:%s,通道id:%s,客户端通过exit命令退出连接\n", new Date(), socketChannel.hashCode());
                }
            } else {
                System.out.printf("date:%s,通道id:%s,没读到了数据，处理别的通道\n", current, socketChannel.hashCode());
            }
        }
        return false;
    }
}
