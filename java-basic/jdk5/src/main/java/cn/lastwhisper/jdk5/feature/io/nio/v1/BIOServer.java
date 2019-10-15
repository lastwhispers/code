package cn.lastwhisper.jdk5.feature.io.v1;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class BIOServer {
    static byte[] bytes = new byte[1024];

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket();){
            serverSocket.bind(new InetSocketAddress(8080));
           while (true){
               System.out.println("wait conn");
               // Server阻塞 等待连接
               Socket socket = serverSocket.accept();
               System.out.println("conn success");
               // 阻塞
               socket.getInputStream().read(bytes);

               System.out.println("data success");
               String content = new String(bytes);
               System.out.println(content);
           }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
