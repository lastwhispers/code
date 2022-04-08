package cn.cunchang;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 
 * @Date: 2021-03-22 15:51
 * @Since
 * @Description:
 */
public class ProxyHandleThread extends Thread{
    private InputStream input;
    private OutputStream output;

    public ProxyHandleThread(InputStream input, OutputStream output, CountDownLatch cdl) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        try {
            while (true) {
                output.write(input.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
