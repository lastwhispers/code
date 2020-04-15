package cn.lastwhisper.feature5.io.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(65000);
            while (true) {
                // 监听请求
                Socket socket = ss.accept();
                new LengthCalculator(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
