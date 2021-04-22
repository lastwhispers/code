package cn.lastwhisper.io.nio.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class BIOServer {
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket();) {
            serverSocket.bind(new InetSocketAddress(8080));
            while (true) {
                System.out.println("wait conn");
                // 等待连接阻塞
                Socket socket = serverSocket.accept();
                System.out.println("conn success");
                // 等待数据阻塞
                socket.getInputStream().read(bytes);

                String content = new String(bytes);
                System.out.println("receive data success || content:" + content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
