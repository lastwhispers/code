package cn.lastwhisper.io.nio.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 应用层做轮询
 * @author lastwhisper
 */
public class MyNIOServer {
    static byte[] bytes = new byte[1024];
    static List<Socket> list = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket();) {
            serverSocket.bind(new InetSocketAddress(8080));
            // 设置serverSocket非阻塞
//             serverSocket.setConfigureBlocking(false);
            while (true) {
                // serverSocket.accept()阻塞
                Socket socket = serverSocket.accept();
                if (socket == null) {
                    System.out.println("没有人连接，处理已有连接");
                    handleSocketList();
                } else {
                    // 设置Socket非阻塞
                    // socket.setConfigureBlocking(false);
                    System.out.println("有人连接，处理已有连接");
                    list.add(socket);
                    handleSocketList();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleSocketList() throws IOException {
        for (Socket s : list) {
            int length = s.getInputStream().read(bytes);
            if (length != 0) {
                String content = new String(bytes);
                System.out.println(content);
            }
        }
    }
}
