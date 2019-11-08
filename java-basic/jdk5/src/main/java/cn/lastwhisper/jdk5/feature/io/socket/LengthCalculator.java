package cn.lastwhisper.jdk5.feature.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author lastwhisper
 */
public class LengthCalculator extends Thread {
    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 获取socket的输出流
        // 获取socket的输入流
        try (OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream();) {
            int ch = 0;
            byte[] buff = new byte[1024];
            // buff主要用来读取输入的内容，存入byte数组，ch读取数组的长度
            ch = is.read(buff);
            // 将接收的byte数组转成字符串
            String content = new String(buff, 0, ch);
            System.out.println(content);

            os.write(String.valueOf(content.length()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
