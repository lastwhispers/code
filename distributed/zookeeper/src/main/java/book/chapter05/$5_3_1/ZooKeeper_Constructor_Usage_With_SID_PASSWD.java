package book.chapter05.$5_3_1;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

//Chapter: 5.3.1 Java API -> 创建连接 -> 创建一个最基本的ZooKeeper对象实例，复用sessionId和
public class ZooKeeper_Constructor_Usage_With_SID_PASSWD implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zookeeper = new ZooKeeper("domain1.book.zookeeper:2181",
                5000, //
                new ZooKeeper_Constructor_Usage_With_SID_PASSWD());
        connectedSemaphore.await();
        long sessionId = zookeeper.getSessionId();
        byte[] passwd = zookeeper.getSessionPasswd();

        //Use illegal sessionId and sessionPassWd
        zookeeper = new ZooKeeper("domain1.book.zookeeper:2181",
                5000, //
                new ZooKeeper_Constructor_Usage_With_SID_PASSWD(),//
                1L,//
                "test".getBytes());
        //Use correct sessionId and sessionPassWd
        zookeeper = new ZooKeeper("domain1.book.zookeeper:2181",
                5000, //
                new ZooKeeper_Constructor_Usage_With_SID_PASSWD(),//
                sessionId,//
                passwd);
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("Receive watched event：" + event);
        if (KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown();
        }
    }
}