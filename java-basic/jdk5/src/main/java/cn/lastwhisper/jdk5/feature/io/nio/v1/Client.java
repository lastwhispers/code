package cn.lastwhisper.jdk5.feature.io.nio.v1;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 三个socket
 * @author lastwhisper
 */
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080);) {
            // client阻塞 等待数据
            Scanner scanner = new Scanner(System.in);
            String txt = scanner.next();

            socket.getOutputStream().write(txt.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
