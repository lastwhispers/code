package cn.lastwhisper.jdk5.feature.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author lastwhisper
 */
public class UDPServer {
    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket(65001);
            byte[] buff = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            // 接收客户端发送的内容，封装到DatagramPacket中
            socket.receive(packet);

            byte[] data = packet.getData();
            // 将字节流转为字符串（输出）
            String content = new String(data, 0, packet.getLength());
            System.out.println(content);
            // 将字符串转为字节流（回写给客户端）
            byte[] bytes = String.valueOf(content.length()).getBytes();

            DatagramPacket packetToClient = new DatagramPacket(bytes, bytes.length, packet.getAddress(), packet.getPort());

            socket.send(packetToClient);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
