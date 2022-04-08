package cn.lastwhisper.concurrent.basic.state;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 线程状态
 *
 * @author cunchang
 * @date 2022/4/4 10:26 PM
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread-TIMED_WAITING").start();
        new Thread(new Waiting(), "WaitingThread-WAITING").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
        new Thread(new IOBlockedState(), "IOBlockedThread-RUNNABLE").start();
        new Thread(new SocketBlockedState(), "SocketBlockedThread-RUNNABLE").start();
    }

    // 该线程不断地进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    // 该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

    // os io阻塞
    static class IOBlockedState implements Runnable {
        Scanner in = new Scanner(System.in);
        @Override
        public void run() {
            try {
                // 命令行中的阻塞读
                String input = in.nextLine();
                System.out.println(input);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(in);
            }
        }
    }

    static class SocketBlockedState implements Runnable {
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(10086);
                while (true) {
                    // 阻塞的accept方法
                    Socket socket = serverSocket.accept();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
