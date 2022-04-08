package cn.cunchang;

import java.net.ServerSocket;

/**
 * @Auther: 
 * @Date: 2021-03-22 15:56
 * @Since
 * @Description:
 */
public class SocketProxy {

    public static void main(String[] args) throws Exception {
        //监听端口
        ServerSocket serverSocket = new ServerSocket(8001);
        System.out.println("8001正在监听..........");
        for (; ; ) {
            new SocketHandle(serverSocket.accept()).start();
        }
    }
}
