package book.chapter05.$5_4_1;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;

// 使用ZkClient来创建一个ZooKeeper客户端
public class Create_Session_Sample {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZkClient zkClient = new ZkClient("domain1.book.zookeeper:2181", 5000);
        System.out.println("ZooKeeper session established.");
    }
}