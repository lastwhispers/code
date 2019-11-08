package cn.lastwhisper.jdk5.feature.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 65000);
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream();) {
            // 将要传递给server的字符串转成byte数组，写到输出流中
            os.write("hello world".getBytes());
            int ch = 0;
            byte[] buff = new byte[1024];
            // buff主要用来读取输入的内容，存入byte数组，ch读取数组的长度
            ch = is.read(buff);
            // 将接收的byte数组转成字符串
            String content = new String(buff, 0, ch);
            System.out.println(content);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
