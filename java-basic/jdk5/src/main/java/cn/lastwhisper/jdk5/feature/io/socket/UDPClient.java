package cn.lastwhisper.jdk5.feature.io.socket;

import java.io.IOException;
import java.net.*;

/**
 * @author lastwhisper
 */
public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] bytes = "Hello World".getBytes();
            // 将ip地址封装成address
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, 65001);
            // 客户端发送数据到服务端
            socket.send(packet);

            // 客户端接收服务端回写的数据报
            byte[] data = new byte[100];
            DatagramPacket receivePacket = new DatagramPacket(data, data.length);
            socket.receive(receivePacket);

            System.out.println(new String(receivePacket.getData(), 0, receivePacket.getLength()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
